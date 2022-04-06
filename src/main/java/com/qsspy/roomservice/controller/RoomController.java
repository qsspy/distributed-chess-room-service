package com.qsspy.roomservice.controller;

import com.qsspy.roomservice.dto.request.CreateRoomRequest;
import com.qsspy.roomservice.dto.request.JoinRoomRequest;
import com.qsspy.roomservice.dto.response.CreateRoomResponse;
import com.qsspy.roomservice.dto.response.GetRoomsResponse;
import com.qsspy.roomservice.dto.response.JoinRoomResponse;
import com.qsspy.roomservice.dto.response.Response;
import com.qsspy.roomservice.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/rooms")
@Slf4j
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Response<CreateRoomResponse> createRoom(@RequestBody final CreateRoomRequest request) {
        log.info("Received create room request : {}", request);
        final CreateRoomResponse response = roomService.createRoom(request);
        return Response.success(response);
    }

    @GetMapping
    public Response<GetRoomsResponse> getRoomList() {
        log.info("Received list rooms request.");
        final GetRoomsResponse response = roomService.getRoomList();
        return Response.success(response);
    }

    @PostMapping("/join")
    public Response<JoinRoomResponse> joinRoom(@RequestBody final JoinRoomRequest request) {
        log.info("Received join room request : {}", request);
        final JoinRoomResponse response = roomService.joinRoom(request);
        return Response.success(response);
    }

    @DeleteMapping
    public Response<Void> deleteRoom(@RequestHeader("Room-Id") final UUID roomId) {
        log.info("Received delete room request for roomId : {}", roomId);
        roomService.deleteRoom(roomId);
        return Response.success();
    }
}
