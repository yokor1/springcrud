package ca.korichi.springcrud.services.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class CrmUser {
    @Id
    private String userId;
    private String name;

    public CrmUser update(CrmUser user) {
        return new CrmUser(userId, user.name);
    }
}
