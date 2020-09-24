package pl.valueadd.permissions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.valueadd.exception.DomainException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PermissionsDeniedException extends DomainException {
    public PermissionsDeniedException(String message) {
        super(message);
    }
}
