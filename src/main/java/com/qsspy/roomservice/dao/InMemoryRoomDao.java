package com.qsspy.roomservice.dao;

import com.qsspy.roomservice.domain.Room;
import com.qsspy.roomservice.exception.NoSuchRoomExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InMemoryRoomDao implements RoomDao{

    private final Map<UUID, Room> rooms = new LinkedHashMap<>();

    @Override
    public synchronized List<Room> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public Room getRoom(final UUID roomId) {
        return rooms.get(roomId);
    }

    @Override
    public synchronized Room createRoom(final Room room) {
        rooms.put(room.getId(), room);
        log.info("New room info stored in data storage.");
        return rooms.get(room.getId());
    }

    @Override
    public synchronized Room editRoom(final Room room) {
        final boolean isStored = Objects.nonNull(rooms.get(room.getId()));
        if(!isStored) {
            throw new NoSuchRoomExistsException(room.getId());
        }

        rooms.put(room.getId(), room);
        return room;
    }

    @Override
    public synchronized Room deleteRoom(final UUID roomId) {
        return rooms.remove(roomId);
    }
}
