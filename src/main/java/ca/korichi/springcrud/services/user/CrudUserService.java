package ca.korichi.springcrud.services.user;

import ca.korichi.springcrud.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
public class CrudUserService implements UserService {
    private final UserRepository userRepository;

    public CrudUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Flux<CrmUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<CrmUser> findById(String userId) {
        return userRepository.findById(userId);
        /*
                .orElseThrow(() -> new CrmUserNotFoundException(
                        String.format("user (%s) not found", userId)));
                        */
    }

    @Transactional
    @Override
    public Mono<CrmUser> create(CrmUser crmUser) {
        return userRepository.save(crmUser);
    }

    @Transactional
    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public Mono<CrmUser> update(String userId, CrmUser user) {

        return findById(userId).flatMap(oldUser -> {
            CrmUser updatedUser = oldUser.update(user);
            return userRepository.save(updatedUser);
        });
    }
}
