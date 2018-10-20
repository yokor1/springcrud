package ca.korichi.springcrud.services.heartbeat;

import ca.korichi.springcrud.controllers.heartbeat.Heartbeat;
import reactor.core.publisher.Mono;

public interface HeartbeatService {
    Mono<Heartbeat> beat(String token);
}
