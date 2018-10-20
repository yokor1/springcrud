package ca.korichi.springcrud.controllers.heartbeat;

import ca.korichi.springcrud.services.heartbeat.HeartbeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HeartbeatController {
    Logger logger = LoggerFactory.getLogger(HeartbeatController.class);

    private HeartbeatService heartbeatService;

    @Autowired
    public HeartbeatController(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @GetMapping(value = "beat")
    public ResponseEntity<Mono<Heartbeat>> beat(@Param("token") String token) {
        logger.info("beat for token= " + token);
        return ResponseEntity.ok(heartbeatService.beat(token));
    }
}
