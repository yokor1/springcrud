package ca.korichi.springcrud.services.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public CrmUser create(CrmUser crmUser) {
        return userRepository.save(crmUser);
    }
}
