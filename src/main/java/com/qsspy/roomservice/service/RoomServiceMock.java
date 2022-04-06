package com.qsspy.roomservice.service;

import com.qsspy.roomservice.dto.request.CreateRoomRequest;
import com.qsspy.roomservice.dto.request.JoinRoomRequest;
import com.qsspy.roomservice.dto.response.CreateRoomResponse;
import com.qsspy.roomservice.dto.response.GetRoomsResponse;
import com.qsspy.roomservice.dto.response.JoinRoomResponse;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Profile("test")
public class RoomServiceMock implements RoomService{
    @Override
    public CreateRoomResponse createRoom(CreateRoomRequest request) {
        return null;
    }

    @Override
    public GetRoomsResponse getRoomList() {
        return null;
    }

    @Override
    public JoinRoomResponse joinRoom(JoinRoomRequest request) {
        return null;
    }

    @Override
    public void deleteRoom(UUID roomId) {

    }
}
