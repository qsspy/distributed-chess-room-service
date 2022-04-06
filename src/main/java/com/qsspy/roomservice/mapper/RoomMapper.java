package com.qsspy.roomservice.mapper;

import com.qsspy.roomservice.domain.Room;
import com.qsspy.roomservice.dto.request.CreateRoomRequest;
import com.qsspy.roomservice.dto.response.GetRoomsResponse;
import com.qsspy.roomservice.dto.response.JoinRoomResponse;
import com.qsspy.roomservice.enums.PlayerColor;
import com.qsspy.roomservice.enums.PlayerType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RoomMapper {

    public static Room toDomain(final CreateRoomRequest request, final UUID roomId, final UUID topicId, final UUID gameOwnerToken) {

        return Room.builder()
                .id(roomId)
                .name(request.getRoomName())
                .password(request.getRoomPassword())
                .joinerColor(request.getOwnerColor().inverse())
                .gameTopicId(topicId)
                .gameOwnerToken(gameOwnerToken)
                .createdAt(new Date().getTime())
                .build();
    }

    public static GetRoomsResponse toGetRoomResponse(final List<Room> rooms) {
        return new GetRoomsResponse(rooms);
    }

    public static JoinRoomResponse toJoinRoomResponse(final Room room, final PlayerType playerType) {
        final JoinRoomResponse.JoinRoomResponseBuilder builder = JoinRoomResponse.builder();
        builder
                .gameTopicId(room.getGameTopicId());
        if(PlayerType.NEW_JOINER == playerType) {
            builder
                    .joinerToken(room.getGameJoinerToken())
                    .playerColor(room.getJoinerColor());
        }
        else if(PlayerType.OWNER == playerType) {
            builder
                    .playerColor(room.getJoinerColor().inverse());
        }
        else {
            builder
                    .playerColor(room.getJoinerColor());
        }
        return builder.build();
    }
}
