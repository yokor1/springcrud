package ca.korichi.springcrud.repositories;


import ca.korichi.springcrud.services.user.CrmUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<CrmUser, String> {
}
