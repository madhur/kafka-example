package app;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelListener extends RouteBuilder {

    @Autowired
    private KafkaConsumerProperties kafkaConsumerProperties;

    @Override
    public void configure() throws Exception {
        from(kafkaConsumerProperties.kafkaUri()).process(exchange -> {

            String payload = exchange.getIn().getBody(String.class);
            System.out.println("Camel consumer: " + payload);
        }).end();
    }


}
