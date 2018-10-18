package ca.korichi.springcrud.services.user;

import ca.korichi.springcrud.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CrudUserService implements UserService {
    private final UserRepository userRepository;

    public CrudUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<CrmUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public CrmUser findById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CrmUserNotFoundException(
                        String.format("user (%s) not found", userId)));
    }

    @Transactional
    @Override
    public CrmUser create(CrmUser crmUser) {
        return userRepository.save(crmUser);
    }

    @Transactional
    @Override
    public void delete(String userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    @Override
    public CrmUser update(String userId, CrmUser user) {
        CrmUser oldUser = findById(userId);
        CrmUser updatedUser = oldUser.update(user);
        userRepository.save(updatedUser);

        return findById(userId);
    }
}
