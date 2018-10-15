package ca.korichi.springcrud.controllers.heartbeat;

import lombok.Value;

@Value
public class Heartbeat {
    String token;
    long timestamp;
}
