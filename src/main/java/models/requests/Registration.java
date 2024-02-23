package models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Registration {

    private String email;
    private String password;
}
