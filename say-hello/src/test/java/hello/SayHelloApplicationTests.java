package hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SayHelloApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SayHelloApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturn200WhenSendingRequestToRoot() {
        @SuppressWarnings("rawtypes") ResponseEntity<String> entity = testRestTemplate
                .getForEntity(
                        "http://localhost:" + port + "/", String.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody()).isEqualTo("Hi!");
    }

    @Test
    void shouldReturn200WhenSendingRequestToGreeting() {
        @SuppressWarnings("rawtypes") ResponseEntity<String> entity = testRestTemplate
                .getForEntity(
                        "http://localhost:" + port + "/greeting", String.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(entity.getBody()).isNotEmpty();
    }
}