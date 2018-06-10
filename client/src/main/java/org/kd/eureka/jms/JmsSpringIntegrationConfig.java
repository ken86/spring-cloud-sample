package org.kd.eureka.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

@Configuration
@EnableIntegration
public class JmsSpringIntegrationConfig {

    String BROKER_URL = "tcp://localhost:61616";
    String BROKER_USERNAME = "admin";
    String BROKER_PASSWORD = "admin";

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUserName(BROKER_USERNAME);
        connectionFactory.setPassword(BROKER_PASSWORD);
        return connectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        return jmsTemplate;
    }

    @Bean
    public DirectChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "inboundChannel", outputChannel = "nullChannel")
    public MessageHandler messageHandler() {
        return new MessageHandler();
    }

    @Bean(destroyMethod = "close")
    @InboundChannelAdapter(channel = "inboundChannel", poller = @Poller(fixedDelay = "2000"))
    public CustomJmsInboundChannelAdapter customJmsInboundChannelAdapter() throws JMSException {
        return new CustomJmsInboundChannelAdapter(jmsTemplate(), activeMQConnectionFactory());
    }

}
