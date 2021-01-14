package fun.box.test.task.controller;

import fun.box.test.task.TestData;
import fun.box.test.task.dto.ErrorResponse;
import fun.box.test.task.dto.SimpleResponse;
import fun.box.test.task.dto.VisitForm;
import fun.box.test.task.dto.VisitView;
import fun.box.test.task.service.api.VisitService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.UriComponentsBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VisitControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private VisitService visitService;

    @Test
    void shouldOk(){
        when(visitService.save(any(VisitForm.class), any(Instant.class))).thenReturn(SimpleResponse.ok());
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "//visited_links",
                VisitForm.of(Arrays.asList("1")),
                SimpleResponse.class).getStatus()).isEqualTo("ok");
    }

    @Test
    void shouldHandleError(){
        when(visitService.save(any(VisitForm.class), any(Instant.class))).thenThrow(new NullPointerException("message"));
        val response = this.restTemplate.postForObject("http://localhost:" + port + "//visited_links", VisitForm.of(Arrays.asList("1")),
                ErrorResponse.class);
        assertThat(response.getStatus()).isEqualTo("Error: message");
    }

    @Test
    void shouldReturnVisits(){
        when(visitService.findByTimeInterval(any(Long.class), any(Long.class))).thenReturn(TestData.newVisitView());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:" + port + "/visited_domains")
                .queryParam("from", 1000L)
                .queryParam("to", 100000L);
        val response = this.restTemplate.getForEntity(builder.build().toUri(), VisitView.class);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getStatus()).isEqualTo("ok");
        assertThat(response.getBody().getDomains().size()).isEqualTo(2);
    }
}
