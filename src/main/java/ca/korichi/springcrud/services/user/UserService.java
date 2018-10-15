package ca.korichi.springcrud.services.user;

import java.util.List;

public interface UserService {
    List<CrmUser> findAll();

    CrmUser create(CrmUser crmUser);

    CrmUser findById(String userId);

    void delete(String userId);

    CrmUser update(String userId, CrmUser user);
}
