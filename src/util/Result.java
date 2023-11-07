package util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    @JsonProperty(index = 1)
    private int code;

    @JsonProperty(index = 2)
    private String msg;

    @JsonProperty(index = 3)
    private T data;
}
