package ca.korichi.springcrud.controllers.user;

import ca.korichi.springcrud.services.user.CrmUser;
import ca.korichi.springcrud.services.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = "/users",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<CrmUser>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(value = "")
    public ResponseEntity<CrmUser> create(@RequestBody CrmUser crmUser) {
        return ResponseEntity.created(URI.create("/users"))
                .body(userService.create(crmUser));
    }
}
