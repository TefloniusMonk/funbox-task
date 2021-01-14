package fun.box.test.task.service.api;

import fun.box.test.task.dto.SimpleResponse;
import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.dto.VisitView;

import java.sql.Timestamp;
import java.time.Instant;

public interface VisitService {
    SimpleResponse save(VisitForm request, Instant time);

    VisitView findByTimeInterval(Long from, Long to);
}
