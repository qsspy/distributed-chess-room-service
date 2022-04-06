package com.qsspy.roomservice.dto.response;

import com.qsspy.roomservice.enums.PlayerColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JoinRoomResponse {
    private UUID joinerToken;
    private UUID gameTopicId;
    private PlayerColor playerColor;
}
