package pl.valueadd.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DomainException extends RuntimeException {

    private final Object[] args;


    public DomainException() {
        this.args = new Object[]{};
    }

    public DomainException(String message, Object...args) {
        super(message);
        this.args = args;
    }
}



