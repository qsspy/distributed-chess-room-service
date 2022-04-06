package com.qsspy.roomservice.messagebroker;

import com.qsspy.roomservice.configuration.KafkaConfiguration;
import com.qsspy.roomservice.mapper.TopicNameMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Profile("!test")
public class KafkaHandler implements MessageBrokerHandler{

    private final KafkaAdmin admin;
    private final AdminClient client;
    private final KafkaConfiguration configuration;

    @Override
    public void createTopic(final UUID topicId) {
        if(!topicExists(topicId)) {
            final NewTopic newTopic = new NewTopic(
                    TopicNameMapper.toTopicName(topicId),
                    configuration.getPartitionsPerTopic(),
                    configuration.getReplicationFactor()
            );
            admin.createOrModifyTopics(newTopic);
            log.info("New kafka topic created.");
        }
    }

    @Override
    public boolean topicExists(final UUID topicId) {
        try {
            admin.describeTopics(TopicNameMapper.toTopicName(topicId));
            return true;
        } catch (final KafkaException exception) {
            return false;
        }
    }

    @Override
    public void deleteTopic(final UUID topicId) {
        if(topicExists(topicId)) {
            client.deleteTopics(
                    Collections.singletonList(
                            TopicNameMapper.toTopicName(topicId)
                    )
            );
        }
    }
}
