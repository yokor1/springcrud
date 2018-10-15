package ca.korichi.springcrud.services.heartbeat;

import ca.korichi.springcrud.controllers.heartbeat.Heartbeat;

public interface HeartbeatService {
    Heartbeat beat(String token);
}
