package fun.box.test.task.service.api;

import java.util.List;

public interface ValidatorService {
    void validate(List<String> urls);

    void validate(String url);
}
