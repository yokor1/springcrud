package ca.korichi.springcrud.services.heartbeat;

import ca.korichi.springcrud.controllers.heartbeat.Heartbeat;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service(value = "HeartbeatService")
public class CrudHeartbeatService implements HeartbeatService {
    @Override
    public Mono<Heartbeat> beat(String token) {
        long timestamp = Instant.now().toEpochMilli();
        return Mono.just(new Heartbeat(token, timestamp));
    }
}
