package entity;

import lombok.Data;

@Data
public class Invitation {
    private Integer id;
    private Integer organization;
    private String organizationName;
    private String state;
}
