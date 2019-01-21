package org.kd.ms.jms;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.jms.JmsMessageDrivenChannelAdapter;
import org.springframework.integration.jms.ChannelPublishingJmsMessageListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import javax.jms.Destination;

@Configuration
public class JmsChannelAdapterIntConfig {

    @Bean
    public JmsMessageDrivenChannelAdapter jmsMessageDrivenChannelAdapter() {
        DefaultMessageListenerContainer defaultMessageListenerContainer =
                new DefaultMessageListenerContainer();

        Destination destination = ActiveMQDestination.createDestination("", (byte)1);

        defaultMessageListenerContainer.setDestination(destination);


        JmsMessageDrivenChannelAdapter jmsMessageDrivenChannelAdapter =
                new JmsMessageDrivenChannelAdapter(new DefaultMessageListenerContainer(),
                        new ChannelPublishingJmsMessageListener());

        return jmsMessageDrivenChannelAdapter;
    }


}
