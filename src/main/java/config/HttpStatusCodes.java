package config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpStatusCodes {
    
    CONTINUE(100),
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    NOT_FOUND(404);

    private final int code;

}
