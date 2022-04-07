package com.qsspy.roomservice.connector;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RestConnector<T>{

    ResponseEntity<T> initRoom(final UUID gameTopicId);

    ResponseEntity<T> deleteRoom(final UUID gameTopicId);
}
