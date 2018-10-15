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
        CrmUser createdUser = userService.create(crmUser);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getUserId()))
                .body(createdUser);
    }

    @GetMapping(value = "/{user-id}")
    public ResponseEntity<CrmUser> findById(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping(value = "/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") String userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{user-id}")
    public ResponseEntity<CrmUser> update(
            @PathVariable("user-id") String userId, @RequestBody CrmUser user) {
        return ResponseEntity.ok(userService.update(userId, user));
    }
}
