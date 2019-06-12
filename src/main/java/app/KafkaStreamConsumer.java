package app;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
@Slf4j
public class KafkaStreamConsumer {

    @Autowired
    private KafkaConsumerProperties kafkaConsumerProperties;

    @Autowired
    private KafkaOrderFeedProcessor kafkaOrderFeedProcessor;

    @Value("${gom.kafka.consumerConfigs[0].topic:Topic2}")
    private String topic;

    @PostConstruct
    public void processKafkaConsumer() {
        Properties properties = kafkaConsumerProperties.getConsumerProperties();
        KafkaStreams kafkaStreams = null;
        try {
            StreamsBuilder builder = new StreamsBuilder();
            KStream<String, String> kStream = builder.stream(topic);
            kStream.process(kafkaOrderFeedProcessor, new String[0]);
            kafkaStreams = new KafkaStreams(builder.build(), properties);
            kafkaStreams.start();
            log.info("op={}, status=OK, desc={}", "KafkaConsumer", "kafka consumer stream  started successfully");
        } catch (Exception var9) {
            log.error("op={}, status=KO, desc={} and exception={}", new Object[]{"KafkaConsumer", "exception while starting kafka consumer stream", var9.getMessage()});
            if (kafkaStreams != null) {
                kafkaStreams.close();
            }
        }

    }

}