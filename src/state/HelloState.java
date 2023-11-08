package state;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class HelloState extends State {
    private static final HelloState INSTANCE = new HelloState();
    private String index;

    private HelloState() {
        index = "LOGIN";
    }

    public static HelloState getInstance() { return INSTANCE; }



}
