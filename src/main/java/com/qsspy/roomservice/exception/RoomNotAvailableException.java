package com.qsspy.roomservice.exception;

import java.util.UUID;

public class RoomNotAvailableException extends RoomServiceException{
    public RoomNotAvailableException(final UUID roomId, final UUID ownerID, final UUID joinerID) {
        super("Can't join room with id : " + roomId + ". Room is taken by players with ids : " + ownerID + " and " + joinerID + ".");
    }
}
