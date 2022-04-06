package com.qsspy.roomservice.service;

import com.qsspy.roomservice.dto.request.CreateRoomRequest;
import com.qsspy.roomservice.dto.request.JoinRoomRequest;
import com.qsspy.roomservice.dto.response.CreateRoomResponse;
import com.qsspy.roomservice.dto.response.GetRoomsResponse;
import com.qsspy.roomservice.dto.response.JoinRoomResponse;

import java.util.UUID;

public interface RoomService {

    CreateRoomResponse createRoom(final CreateRoomRequest request);

    GetRoomsResponse getRoomList();

    JoinRoomResponse joinRoom(final JoinRoomRequest request);

    void deleteRoom(final UUID roomId);
}
