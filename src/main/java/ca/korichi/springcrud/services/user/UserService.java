package ca.korichi.springcrud.services.user;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Flux<CrmUser> findAll();

    Mono<CrmUser> create(CrmUser crmUser);

    Mono<CrmUser> findById(String userId);

    void delete(String userId);

    Mono<CrmUser> update(String userId, CrmUser user);
}
