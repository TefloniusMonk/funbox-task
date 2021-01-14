package fun.box.test.task.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse extends SimpleResponse {
    private static final String ERROR = "Error: ";

    public ErrorResponse(String status) {
        super(status);
    }

    public static ErrorResponse build(String message) {
        return new ErrorResponse(ERROR + message);
    }
}
