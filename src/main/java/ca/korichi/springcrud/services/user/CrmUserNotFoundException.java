package ca.korichi.springcrud.services.user;

public class CrmUserNotFoundException extends RuntimeException {
    public CrmUserNotFoundException(String message) {
        super(message);
    }
}
