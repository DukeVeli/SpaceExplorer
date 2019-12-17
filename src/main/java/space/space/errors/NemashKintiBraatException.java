package space.space.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Nemash kinti braat!")
public class NemashKintiBraatException extends RuntimeException {
    public NemashKintiBraatException(String message){
        super((message));

    }
}
