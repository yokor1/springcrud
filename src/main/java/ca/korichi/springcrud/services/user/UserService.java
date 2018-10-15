package ca.korichi.springcrud.services.user;

import java.util.List;

public interface UserService {
    List<CrmUser> findAll();

    CrmUser create(CrmUser crmUser);
}
