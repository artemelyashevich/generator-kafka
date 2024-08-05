package com.elyashevich.analyser_microservices.config;

import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    @Value("${topics}")
    private List<String> topics;

    private final XML settings;

    @Bean
    public Map<String, Object> receiverProperties() {
        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.servers,
                ConsumerConfig.GROUP_ID_CONFIG, new TextXPath(
                        this.settings, "//groupId"
                )
                        .toString(),
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, new TextXPath(
                        this.settings, "//keyDeserializer"
                )
                        .toString(),
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new TextXPath(
                        this.settings, "//valueDeserializer"
                )
                        .toString(),
                "spring.json.trusted.packages", new TextXPath(
                        this.settings, "//trustedPackages"
                )
                        .toString()
        );
    }

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        final ReceiverOptions<String, Object> receiverOptions = ReceiverOptions
                .create(
                        this.receiverProperties()
                );
        return receiverOptions
                .subscription(this.topics)
                .addAssignListener(partitions ->
                        System.out.printf("Assigned: %s%n", partitions))
                .addRevokeListener(partitions ->
                        System.out.printf("Revoked: %s%n", partitions));
    }

    @Bean
    public KafkaReceiver<String, Object> receiver(
            final ReceiverOptions<String, Object> receiverOptions
    ) {
        return KafkaReceiver.create(receiverOptions);
    }
}
