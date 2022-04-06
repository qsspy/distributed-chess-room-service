package com.qsspy.roomservice.messagebroker;

import java.util.UUID;

public interface MessageBrokerHandler {

    void createTopic(final UUID topicId);

    boolean topicExists(final UUID topicId);

    void deleteTopic(final UUID topicId);
}
