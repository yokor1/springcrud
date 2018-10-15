package ca.korichi.springcrud.services.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<CrmUser, String> {
}
