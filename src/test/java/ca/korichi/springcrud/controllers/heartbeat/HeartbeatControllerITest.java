package ca.korichi.springcrud.controllers.heartbeat;

import ca.korichi.springcrud.services.heartbeat.HeartbeatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeartbeatControllerITest {

    private final String a_token = "a_token";
    private final long a_timestamp = 100;
    private final Heartbeat a_heartbeat = new Heartbeat(a_token, a_timestamp);

    @MockBean
    HeartbeatService heartbeatService;

    @Autowired
    HeartbeatController heartbeatController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(heartbeatController)
                .build();

        willReturn(a_heartbeat).given(heartbeatService).beat(a_token);
    }

    @Test
    public void contextLoads() {
        assertNotNull(heartbeatController);
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithJsonApplicationMediaType()
            throws Exception {

        mockMvc.perform(
                get("/beat")
                        .param("token", a_token)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithTimestamp()
            throws Exception {

        mockMvc.perform(
                get("/beat")
                        .param("token", a_token)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.timestamp").value(a_timestamp));
    }

    @Test
    public void givenAToken_whenBeatIsRequestedWithThisToken_thenABeatIsReturnedWithThisToken()
            throws Exception {

        mockMvc.perform(
                get("/beat")
                        .param("token", a_token)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.token").value(a_token));
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithStatusOk()
            throws Exception {

        mockMvc.perform(
                get("/beat")
                        .param("token", a_token)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }
}