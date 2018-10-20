package ca.korichi.springcrud.controllers.user;

import ca.korichi.springcrud.services.user.CrmUser;
import ca.korichi.springcrud.services.user.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(
            value = "",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Flux<CrmUser>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping(
            value = "",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Mono<CrmUser>> create(@RequestBody CrmUser crmUser) {
        final String[] userId = new String[1];
        Mono<CrmUser> createdUser = userService.create(crmUser).map(user -> {
            userId[0] = user.getUserId();
            return user;
        });
        return ResponseEntity.created(URI.create("/users/" + userId[0]))
                .body(createdUser);
    }

    @GetMapping(
            value = "/{userid}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Mono<CrmUser>> findById(@PathVariable("userid") String userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @DeleteMapping(value = "/{userid}")
    public ResponseEntity<Void> delete(@PathVariable("userid") String userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(
            value = "/{userid}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Mono<CrmUser>> update(
            @PathVariable("userid") String userId, @RequestBody CrmUser user) {
        return ResponseEntity.ok(userService.update(userId, user));
    }
}
