package fun.box.test.task.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BusinessLogicException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }
}
