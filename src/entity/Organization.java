package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private Integer id;
    private Integer creator;
    private String name;

    public boolean belongTo(Integer account) { return Objects.equals(creator, account);}
}
