package models.responses;

import lombok.Data;

@Data
public class EmployeeCreationResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
