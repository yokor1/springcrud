package ca.korichi.springcrud.controllers.heartbeat;

import ca.korichi.springcrud.services.heartbeat.HeartbeatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "/",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
@Api(tags = "heartbeat", description = "Heartbeat API")
public class HeartbeatController {
    private HeartbeatService heartbeatService;

    @Autowired
    public HeartbeatController(HeartbeatService heartbeatService) {
        this.heartbeatService = heartbeatService;
    }

    @GetMapping(value = "beat")
    @ApiOperation(value = "Get beat", notes = "Get a beat to check if the server is alive")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "heartbeat "),
    })
    public ResponseEntity<Heartbeat> beat(@Param("token") String token) {
        return ResponseEntity.ok(
                heartbeatService
                        .beat(token));
    }
}
