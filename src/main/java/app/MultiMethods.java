package app;

import app.domain.Bar;
import app.domain.Foo1;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(id = "multiGroup", topics = { "foos", "bars" })
public class MultiMethods {

    @KafkaHandler
    public void foo(String foo) {
        System.out.println("Received: " + foo);
    }



}
