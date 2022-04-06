package com.qsspy.roomservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qsspy.roomservice.enums.PlayerColor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.util.Objects.nonNull;
import static java.util.Objects.hash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @JsonProperty("roomId")
    private UUID id;
    @JsonProperty("roomName")
    private String name;
    @JsonIgnore
    private String password;
    private PlayerColor joinerColor;
    @JsonIgnore
    private UUID gameTopicId;
    @JsonIgnore
    private UUID gameOwnerToken;
    @JsonIgnore
    private UUID gameJoinerToken;
    private long createdAt;

    public boolean hasBothPlayersAssigned() {
        return nonNull(gameJoinerToken) && nonNull(gameOwnerToken);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return hash(id);
    }
}
