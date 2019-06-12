package app;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class KafkaOrderFeedProcessor implements ProcessorSupplier {

    @Override
    public Processor get() {
        Processor processor = new Processor<String, String>() {

            @Override
            public void init(ProcessorContext processorContext) {
                log.info("Kafka processor started");
            }

            @Override
            public void process(String key, String payload) {
                try {
                    System.out.println("Kafka streams consumer: " + payload);
                } catch (Exception exception) {
                }
            }

            @Override
            public void close() {
                log.info("Kafka processor closed");
            }
        };

        return processor;
    }
}
