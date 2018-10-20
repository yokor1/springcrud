package ca.korichi.springcrud.controllers.user;

import ca.korichi.springcrud.services.user.CrmUser;
import ca.korichi.springcrud.services.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = UserController.class)
class UserControllerITest {

    @Autowired
    private UserController userController;
    @MockBean
    private UserService userService;
    @Autowired
    private WebTestClient webClient;

    @Test
    public void contextLoads() {
        assertNotNull(userController);
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithJsonApplicationMediaType() {
        willReturn(Flux.empty()).given(userService).findAll();
        webClient.get().uri("/users")
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8);
    }

    @Test
    public void whenUsersAreRequested_thenAStatusOkIsReturned() {
        willReturn(Flux.empty()).given(userService).findAll();
        webClient.get().uri("/users")
                .exchange().expectStatus().isOk();
    }

    @Test
    public void whenUsersAreRequested_thenAListOfTheRightSizeIsReturned() {
        List<CrmUser> users = getUserList();
        willReturn(Flux.fromIterable(users)).given(userService).findAll();
        webClient.get().uri("/users")
                .exchange()
                .expectBodyList(CrmUser.class)
                .hasSize(2);
    }

    @Test
    public void whenUserIsRequestedById_thenTheUserIsReturned() throws Exception {
        String id = "id";
        String name = "name";
        CrmUser user = new CrmUser(id, name);
        willReturn(Mono.just(user)).given(userService).findById(id);

        webClient.get().uri("/users/{userid}", id)
                .exchange()
                .expectBody(CrmUser.class)
                .isEqualTo(user);
    }

    private List<CrmUser> getUserList() {
        CrmUser user1 = new CrmUser("id1", "user1");
        CrmUser user2 = new CrmUser("id2", "user2");

        return new ArrayList<>(Arrays.asList(user1, user2));
    }

}