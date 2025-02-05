package digital.softwareshinobi.napkinexchange.security.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such stock exists")
public class SecurityNotFoundException extends RuntimeException {

    public SecurityNotFoundException(String message) {
        super(message);
    }
}
