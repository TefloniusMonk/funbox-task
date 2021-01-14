package fun.box.test.task.service;

import fun.box.test.task.TestData;
import fun.box.test.task.db.Visit;
import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.service.api.VisitService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Instant;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class VisitServiceTest {
    private static final String TABLE_KEY = "VisitTimestamp";

    @Autowired
    private VisitService visitService;

    @Autowired
    private RedisTemplate<String, Visit> visitTemplate;

    @BeforeEach
    void before() {
        visitTemplate.opsForZSet().removeRange(TABLE_KEY, 0, -1);
    }

    @Test
    void shouldSave() {
        val response = visitService.save(TestData.newVisitForm(), Instant.now());
        Set<Visit> visits = visitTemplate.opsForZSet().range(TABLE_KEY, 0L, -1L);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo("ok");
        assertThat(visits).isNotNull();
        assertThat(visits.size()).isEqualTo(1);
        assertThat(visits.iterator().next().getLinks().size()).isEqualTo(2);
    }

    @Test
    void shouldSaveWithSameInstant() {
        Instant now = Instant.now();
        visitService.save(TestData.newVisitForm(), now);
        visitService.save(TestData.newVisitForm2(), now);
        Set<Visit> visits = visitTemplate.opsForZSet().range(TABLE_KEY, 0L, -1L);
        assertThat(visits).isNotNull();
        assertThat(visits.size()).isEqualTo(2);
    }

    @Test
    void shouldReturnDomains() {
        Instant now = Instant.now();
        visitService.save(TestData.newVisitForm3(), now);
        val view = visitService.findByTimeInterval(now.minusSeconds(1000).getEpochSecond(), now.plusSeconds(1000).getEpochSecond());
        assertThat(view).isNotNull();
        assertThat(view.getDomains().size()).isEqualTo(3);
        assertThat(view.getDomains()).doesNotContainNull();
        assertThat(view.getDomains()).containsAll(Arrays.asList("funbox.ru", "stackoverflow.com", "ya.ru"));
    }

    @Test
    void shouldFilterDomains() {
        Instant now = Instant.now();
        Instant then = Instant.now().minusSeconds(100000L);
        visitService.save(TestData.newVisitForm3(), then);
        visitService.save(TestData.newVisitForm4(), now);
        val view = visitService.findByTimeInterval(now.minusSeconds(1000).getEpochSecond(), now.plusSeconds(1000).getEpochSecond());
        assertThat(view).isNotNull();
        assertThat(view.getDomains().size()).isEqualTo(2);
        assertThat(view.getDomains()).doesNotContainNull();
        assertThat(view.getDomains()).containsAll(Arrays.asList("ру.рф", "google.com"));
    }
}
