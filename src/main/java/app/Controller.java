package app;

import app.domain.Bar;
import app.domain.Foo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class Controller {

    @Autowired
    private KafkaTemplate<Object, Object> template;

    @GetMapping(path = "/send/foo/{what}")
    public void sendFoo(@PathVariable String what) {
        this.template.send("Topic2", what);
    }

    @GetMapping(path = "/send/bar/{what}")
    public void sendBar(@PathVariable String what) {
        this.template.send(new GenericMessage<>(what,
                Collections.singletonMap(KafkaHeaders.TOPIC, "bars")));
    }

    @GetMapping(path = "/send/unknown/{what}")
    public void sendUnknown(@PathVariable String what) {
        this.template.send(new GenericMessage<>(what,
                Collections.singletonMap(KafkaHeaders.TOPIC, "foos")));
    }

}
