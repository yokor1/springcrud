package ca.korichi.springcrud.controllers.user;

import ca.korichi.springcrud.SpringcrudApplication;
import ca.korichi.springcrud.services.user.CrmUser;
import ca.korichi.springcrud.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.willReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringcrudApplication.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerITest {

    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @MockBean
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    public void contextLoads() {
        assertNotNull(userController);
    }

    @Test
    public void whenBeatIsRequested_thenABeatIsReturnedWithJsonApplicationMediaType()
            throws Exception {
        willReturn(new ArrayList<>()).given(userService).findAll();
        mockMvc.perform(
                get("/users").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }


    @Test
    public void whenUsersAreRequested_thenAStatusOkIsReturned() throws Exception {
        willReturn(new ArrayList<>()).given(userService).findAll();
        mockMvc.perform(
                get("/users").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void whenUsersAreRequested_thenTheListOfUsersIsReturned() throws Exception {
        List<CrmUser> users = getUserList();
        willReturn(users).given(userService).findAll();
        mockMvc.perform(
                get("/users").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    private List<CrmUser> getUserList() {
        CrmUser user1 = new CrmUser("id1", "user1");
        CrmUser user2 = new CrmUser("id2", "user2");

        return new ArrayList<>(Arrays.asList(user1, user2));
    }

}