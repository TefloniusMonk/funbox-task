package fun.box.test.task.mapper;

import fun.box.test.task.db.Visit;
import fun.box.test.task.dto.VisitForm;
import lombok.val;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class VisitMapper implements Mapper<Visit, VisitForm>{

    @Override
    public Visit fromForm(VisitForm form) {
        val visit = new Visit();
        visit.setLinks(form.getLinks());
        visit.setTimestamp(Instant.now().toString());
        return visit;
    }

    @Override
    public VisitForm toForm(Visit entity) {
        return VisitForm.of(entity.getLinks());
    }
}
