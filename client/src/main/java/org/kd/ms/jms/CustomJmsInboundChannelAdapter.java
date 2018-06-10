package org.kd.ms.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.jms.JmsDestinationPollingSource;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

public class CustomJmsInboundChannelAdapter extends JmsDestinationPollingSource {

    private static final Logger logger = LoggerFactory.getLogger(CustomJmsInboundChannelAdapter.class);

    private ActiveMQConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private Destination destination;

    public CustomJmsInboundChannelAdapter(final JmsTemplate jmsTemplate,
                                          final ActiveMQConnectionFactory connectionFactory) throws JMSException {
        super(jmsTemplate);
        this.connectionFactory = connectionFactory;
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue("test.queue.1");
        this.setDestination(destination);
    }

    public void close() {
        logger.info("Releasing resource.");
        try {
            if (session != null) {
                session.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            logger.error("Error releasing resource.");
        }
    }

}
