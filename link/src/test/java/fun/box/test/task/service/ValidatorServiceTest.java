package fun.box.test.task.service;

import fun.box.test.task.TestData;
import fun.box.test.task.service.api.ValidatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    private ValidatorService validatorService;

    @Test
    void shouldValidate(){
        validatorService.validate(TestData.validUrls());
    }

    @Test
    void shouldFailAll(){
        TestData.invalidUrls().forEach(url -> {
            assertThatThrownBy(() -> validatorService.validate(url));
        });
    }
}
