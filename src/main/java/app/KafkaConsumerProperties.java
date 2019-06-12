package app;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaConsumerProperties {

    @Value("${kafka.sfm.guestCancel.kafkaconnect:localhost:9092}")
    private String kafkaConnect;

    @Value("${kafka.sfm.guestCancel.topic:Topic2}")
    private String kafkaSfmGuestCancelTopic;

    @Value("${kafka.sfm.guestCancel.consumergroup:sfm_guest_cancel_service_group}")
    private String kafkasfmGuestCancelConsumergroup;

    @Value("${kafka.autoOffsetReset:earliest}")
    private String autoOffsetReset;

    @Value("${kafka.ssltrust.location:test_path}")
    private String kafkasfmGuestCancelSSLTruststoreLocation;

    @Value("${kafka.ssltrust.password:password}")
    private String kafkasfmGuestCancelSSLTruststorePassword;

    @Value("${kafka.sslkeys.location:test_path}")
    private String kafkasfmGuestCancelSSLKeystoreLocation;

    @Value("${kafka.sslkeys.password:password}")
    private String kafkasfmGuestCancelSSLKeystorePassword;

    @Value("${kafka.consumerCount:1}")
    private String consumerCount;

    @Value("${kafka.consumerStream:1}")
    private String consumerStream;

    @Value("${kafka.consumerRequestTimeoutMs:450000}")
    private String consumerRequestTimeoutMs;

    @Value("${kafka.sessionTimeoutMs:300000}")
    private String sessionTimeoutMs;


    public String kafkaUri() {
        String uri = "kafka:" + kafkaConnect
                + "?topic=" + kafkaSfmGuestCancelTopic
                + "&groupId=" + kafkasfmGuestCancelConsumergroup
                + "&autoOffsetReset=" + autoOffsetReset
                + "&consumersCount=" + consumerCount
                + "&consumerStreams=" + consumerStream
                + "&consumerRequestTimeoutMs=" + consumerRequestTimeoutMs
                + "&sessionTimeoutMs=" + sessionTimeoutMs
                + "&serializerClass=org.apache.kafka.common.serialization.StringSerializer";
        return uri;
    }

    public Properties getConsumerProperties() {
        Properties props = new Properties();
        this.setProperty(props, "bootstrap.servers", kafkaConnect);
        this.setProperty(props, "application.id", "id");
        this.setProperty(props, "default.key.serde", Serdes.String().getClass());
        this.setProperty(props, "default.value.serde", Serdes.String().getClass());

        return props;
    }

    private void setProperty(Properties prop, Object key, Object value) {
        if (key != null && value != null) {
            prop.put(key, value);
        }

    }
}
