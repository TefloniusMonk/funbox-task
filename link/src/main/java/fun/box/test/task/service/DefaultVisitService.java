package fun.box.test.task.service;

import fun.box.test.task.db.Visit;
import fun.box.test.task.dto.SimpleResponse;
import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.dto.VisitView;
import fun.box.test.task.exception.BusinessLogicException;
import fun.box.test.task.mapper.Mapper;
import fun.box.test.task.service.api.ValidatorService;
import fun.box.test.task.service.api.VisitService;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultVisitService implements VisitService {
    private static final String TABLE_KEY = "VisitTimestamp";
    private final ValidatorService validatorService;
    private final RedisTemplate<String, Visit> visitTemplate;
    private final Mapper<Visit, VisitForm> mapper;

    @Override
    public SimpleResponse save(VisitForm request, Instant time) {
        validatorService.validate(request.getLinks());
        visitTemplate.opsForZSet().add(TABLE_KEY, mapper.fromForm(request), time.getEpochSecond());
        return SimpleResponse.ok();
    }

    @Override
    public VisitView findByTimeInterval(Long from, Long to) {
        return VisitView.of(domainsFromInterval(from, to)).withStatusOk();
    }

    private Set<String> domainsFromInterval(Long from, Long to) {
        return getVisitsOrEmptyList(from, to).stream()
                .flatMap(visit -> visit.getLinks().stream())
                .map(link -> {
                    String[] removedProtocol = link.split("://");
                    link = removedProtocol.length == 1 ? removedProtocol[0] : removedProtocol[1];
                    return link.split("/|\\?")[0];
                })
                .collect(Collectors.toSet());
    }

    private Set<Visit> getVisitsOrEmptyList(Long from, Long to) {
        return visitTemplate.opsForZSet().rangeByScore(TABLE_KEY, from, to);
    }
}
