package models.responses;

import lombok.Data;

@Data
public class EmployeeUpdateResponse {
    private String name;
    private String job;
    private String updatedAt;
}
