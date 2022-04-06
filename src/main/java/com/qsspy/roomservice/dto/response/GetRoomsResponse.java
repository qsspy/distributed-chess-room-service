package com.qsspy.roomservice.dto.response;

import com.qsspy.roomservice.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRoomsResponse {
    private List<Room> rooms;
}
