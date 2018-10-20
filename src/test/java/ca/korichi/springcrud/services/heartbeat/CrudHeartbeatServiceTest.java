package ca.korichi.springcrud.services.heartbeat;

import ca.korichi.springcrud.controllers.heartbeat.Heartbeat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class CrudHeartbeatServiceTest {

    private HeartbeatService crudHeartbeatService;

    @BeforeEach
    void setUp() {
        crudHeartbeatService = new CrudHeartbeatService();
    }

    @Test
    public void test() {

        final String a_token = "a_token";
        crudHeartbeatService.beat(a_token)
                .map(Heartbeat::getToken)
                .subscribe(token -> assertEquals(a_token, token));
    }

}