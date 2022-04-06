package com.qsspy.roomservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JoinRoomRequest {
    private UUID roomId;
    private UUID accessToken; //optional
    private String roomPassword;
}
