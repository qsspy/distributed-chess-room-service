package com.qsspy.roomservice.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@ConfigurationProperties("chess-command")
@Data
@Slf4j
@Profile("!test")
public class ChessCommandConnectorConfiguration {
    private String address;
    private String initRoomEndpoint;
    private String deleteRoomEndpoint;

    @PostConstruct
    public void post() {
        log.info("Created chess-command configuration : {}", this);
    }
}