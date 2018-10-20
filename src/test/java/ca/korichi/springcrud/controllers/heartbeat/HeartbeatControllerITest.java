package ca.korichi.springcrud.controllers.heartbeat;

import ca.korichi.springcrud.services.heartbeat.HeartbeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = HeartbeatController.class)
public class HeartbeatControllerITest {

    private final String a_token = "a_token";
    private final long a_timestamp = 100;
    private final Heartbeat a_heartbeat = new Heartbeat(a_token, a_timestamp);

    @MockBean
    HeartbeatService heartbeatService;

    @Autowired
    HeartbeatController heartbeatController;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    public void setup() {

        willReturn(Mono.just(a_heartbeat)).given(heartbeatService).beat(a_token);
    }

    @Test
    public void contextLoads() {
        assertNotNull(heartbeatController);
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithJsonApplicationMediaType() {
        baseRequest().expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithStatusOk() {
        baseRequest().expectStatus().isOk();

    }


    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithTimestamp() {
        baseRequest().expectBody().jsonPath("$.timestamp").isEqualTo(a_timestamp);
    }

    @Test
    public void givenAToken_whenBeatIsRequestedWithThisToken_thenABeatIsReturnedWithThisToken() {
        baseRequest().expectBody().jsonPath("$.token").isEqualTo(a_token);

    }

    private WebTestClient.ResponseSpec baseRequest() {
        return webClient.get()
                .uri("/beat?token=" + a_token)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .exchange();
    }
}