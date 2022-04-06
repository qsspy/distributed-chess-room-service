package com.qsspy.roomservice.exception;

import java.util.UUID;

public class InvalidPasswordException extends RoomServiceException{
    public InvalidPasswordException(final String roomName, final UUID roomId, final String passedPassword) {
        super("Wrong password '" + passedPassword + "' for roomName : " + roomName + ", roomId : " + roomId + ".");
    }
}
