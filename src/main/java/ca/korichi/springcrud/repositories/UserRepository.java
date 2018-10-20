package ca.korichi.springcrud.repositories;


import ca.korichi.springcrud.services.user.CrmUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserRepository extends ReactiveMongoRepository<CrmUser, String> {
}
