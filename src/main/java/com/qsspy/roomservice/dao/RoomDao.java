package com.qsspy.roomservice.dao;

import com.qsspy.roomservice.domain.Room;

import java.util.List;
import java.util.UUID;

public interface RoomDao {

    List<Room> getRooms();

    Room getRoom(final UUID roomId);

    Room createRoom(final Room room);

    Room editRoom(final Room room);

    Room deleteRoom(final UUID roomId);
}
