package com.qsspy.roomservice.exception;

import java.util.UUID;

public class InvalidAccessTokenException extends RoomServiceException{

    public InvalidAccessTokenException(final UUID roomId, final UUID accessToken) {
        super("User with token " + accessToken + " is not owner or joiner of room with id " + roomId + ".");
    }
}
