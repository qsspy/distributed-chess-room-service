package com.qsspy.roomservice.dto.request;

import com.qsspy.roomservice.enums.PlayerColor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest {
    private PlayerColor ownerColor;
    private String roomName;
    private String roomPassword;
}
