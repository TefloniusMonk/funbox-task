package fun.box.test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleResponse {
    protected static final String OK = "ok";
    protected String status;

    public static SimpleResponse ok(){
        return new SimpleResponse(OK);
    }
}
