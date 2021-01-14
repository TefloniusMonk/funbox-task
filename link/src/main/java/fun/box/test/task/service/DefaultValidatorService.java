package fun.box.test.task.service;

import fun.box.test.task.exception.BusinessLogicException;
import fun.box.test.task.service.api.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DefaultValidatorService implements ValidatorService {
    private final UrlValidator urlValidator;

    public DefaultValidatorService() {
        urlValidator = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
    }

    @Override
    public void validate(List<String> urls) {
        urls.forEach(this::validate);
    }

    @Override
    public void validate(String url) {
        String toValidate = url;
        if (!url.contains("://")) {
            toValidate = "http://" + url;
        }
        if (!urlValidator.isValid(toValidate)) {
            log.error("Url is invalid: {}", url);
            throw new BusinessLogicException(String.format("Incorrect url pattern {%s}", url));
        }
    }
}
