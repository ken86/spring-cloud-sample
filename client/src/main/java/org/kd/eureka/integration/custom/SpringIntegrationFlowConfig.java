package org.kd.eureka.integration.custom;

import org.kd.eureka.client.domain.Journal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class SpringIntegrationFlowConfig {

    @Bean
    public MessageChannel inboundDataChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(poller = @Poller(fixedDelay = "20000"))
    public MessageSource<Journal> personDataAdapter() {
        CustomDataAdapter customDataAdapter = new CustomDataAdapter();
        return customDataAdapter;
    }
}
