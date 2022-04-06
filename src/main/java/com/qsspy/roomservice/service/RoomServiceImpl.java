package com.qsspy.roomservice.service;

import com.qsspy.roomservice.dao.RoomDao;
import com.qsspy.roomservice.domain.Room;
import com.qsspy.roomservice.dto.request.CreateRoomRequest;
import com.qsspy.roomservice.dto.request.JoinRoomRequest;
import com.qsspy.roomservice.dto.response.CreateRoomResponse;
import com.qsspy.roomservice.dto.response.GetRoomsResponse;
import com.qsspy.roomservice.dto.response.JoinRoomResponse;
import com.qsspy.roomservice.enums.PlayerType;
import com.qsspy.roomservice.exception.*;
import com.qsspy.roomservice.mapper.RoomMapper;
import com.qsspy.roomservice.messagebroker.MessageBrokerHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@Slf4j
@RequiredArgsConstructor
@Profile("!test")
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;
    private final MessageBrokerHandler brokerHandler;

    @Override
    public CreateRoomResponse createRoom(final CreateRoomRequest request) {
        final UUID roomId = UUID.randomUUID();
        final UUID topicId = UUID.randomUUID();
        final UUID gameOwnerToken = UUID.randomUUID();
        final Room newRoom = RoomMapper.toDomain(request, roomId, topicId, gameOwnerToken);
        roomDao.createRoom(newRoom);
        brokerHandler.createTopic(topicId);
        log.info("Room created successfully : {}", newRoom);
        return new CreateRoomResponse(gameOwnerToken);
    }

    @Override
    public GetRoomsResponse getRoomList() {
        final List<Room> roomList = roomDao.getRooms();
        log.info("Returning room list : {}", roomList);
        return RoomMapper.toGetRoomResponse(roomList);
    }

    @Override
    public JoinRoomResponse joinRoom(final JoinRoomRequest request) {
        final Room room = roomDao.getRoom(request.getRoomId());
        if(Optional.ofNullable(room).isPresent()) {
            //wrong password
            if(!room.getPassword().equals(request.getRoomPassword())) {
                throw new InvalidPasswordException(room.getName(), room.getId(), request.getRoomPassword());
            }
            if(isNull(request.getAccessToken())) {
                //attempt to join room with no accessToken passed, but room is already taken
                if(room.hasBothPlayersAssigned()) {
                    throw new RoomNotAvailableException(room.getId(), room.getGameOwnerToken(), room.getGameJoinerToken());
                }

                //attempt to join room with no accessToken passed, but joiner slot is free
                log.info("Joined as new room joiner. Joiner access token will be stored for this room.");
                final UUID newJoinerToken = UUID.randomUUID();
                room.setGameJoinerToken(newJoinerToken);
                roomDao.editRoom(room);
                return RoomMapper.toJoinRoomResponse(room, PlayerType.NEW_JOINER);
            }

            //room owner joined
            if(request.getAccessToken().equals(room.getGameOwnerToken())) {
                log.info("Joined as room owner.");
                return RoomMapper.toJoinRoomResponse(room, PlayerType.OWNER);
            }
            //room joiner joined
            if(request.getAccessToken().equals(room.getGameJoinerToken())) {
                log.info("Joined as room joiner.");
                return RoomMapper.toJoinRoomResponse(room, PlayerType.JOINER);
            }
            //else wrong access token
            throw new InvalidAccessTokenException(room.getId(), request.getAccessToken());
        }

        throw new RoomServiceException("Room with id : " + request.getRoomId() + " does not exists!");
    }

    @Override
    public void deleteRoom(final UUID roomId) {
        Optional.ofNullable(roomDao.getRoom(roomId))
                .map(Room::getGameTopicId)
                .ifPresentOrElse(
                        topicId -> this.removeRoom(topicId, roomId),
                        () -> {throw new NoSuchRoomExistsException(roomId);}
                );
    }

    private void removeRoom(final UUID topicId, final UUID roomId) {
        brokerHandler.deleteTopic(topicId);
        roomDao.deleteRoom(roomId);
        log.info("Room with id " + roomId + "removed successfully!");
    }
}