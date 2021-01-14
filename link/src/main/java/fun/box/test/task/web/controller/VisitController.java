package fun.box.test.task.web.controller;

import fun.box.test.task.dto.SimpleResponse;
import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.dto.VisitView;
import fun.box.test.task.service.api.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@AllArgsConstructor
public class VisitController {
    private final VisitService visitService;

    @PostMapping("/visited_links")
    public SimpleResponse saveVisits(@RequestBody VisitForm request) {
        return visitService.save(request, Instant.now());
    }

    @GetMapping("/visited_domains")
    public VisitView findDomains(@RequestParam("from") Long from, @RequestParam("to") Long to) {
        return visitService.findByTimeInterval(from, to);
    }
}
