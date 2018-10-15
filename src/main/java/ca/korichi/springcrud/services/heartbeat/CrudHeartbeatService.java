package ca.korichi.springcrud.services.heartbeat;

import ca.korichi.springcrud.controllers.heartbeat.Heartbeat;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class CrudHeartbeatService implements HeartbeatService {
    @Override
    public Heartbeat beat(String token) {
        long timestamp = Instant.now().toEpochMilli();
        return new Heartbeat(token, timestamp);
    }
}
