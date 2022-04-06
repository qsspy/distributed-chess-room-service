package com.qsspy.roomservice.exception;

import java.util.UUID;

public class NoSuchRoomExistsException extends RoomServiceException{
    public NoSuchRoomExistsException(final UUID roomId) {
        super("Room with id : " + roomId + " doesn't exists.");
    }
}
