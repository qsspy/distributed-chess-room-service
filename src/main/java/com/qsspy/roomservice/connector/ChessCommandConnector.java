package com.qsspy.roomservice.connector;

import com.qsspy.roomservice.configuration.ChessCommandConnectorConfiguration;
import com.qsspy.roomservice.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class ChessCommandConnector implements RestConnector<Response>{

    private static final String GAME_ID_HEADER = "Game-Topic-Id";

    private final RestTemplate restTemplate;
    private final ChessCommandConnectorConfiguration config;

    @Override
    public ResponseEntity<Response> initRoom(final UUID gameTopicId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(GAME_ID_HEADER, gameTopicId.toString());
        final HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(buildInitUrl(), HttpMethod.GET, entity, Response.class, (Object) null);
    }

    @Override
    public ResponseEntity<Response> deleteRoom(final UUID gameTopicId) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(GAME_ID_HEADER, gameTopicId.toString());
        final HttpEntity<Void> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(buildDeleteUrl(), HttpMethod.DELETE, entity, Response.class, (Object) null);

    }

    private String buildInitUrl() {
        return this.buildUrl(config.getInitRoomEndpoint());
    }

    private String buildDeleteUrl() {
        return this.buildUrl(config.getDeleteRoomEndpoint());
    }

    private String buildUrl(final String endpoint) {
        return "" + config.getAddress() + endpoint;
    }
}
