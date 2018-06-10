package org.kd.ms.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.Session;

public class JmsConnectionWrapper {

    private static final Logger logger = LoggerFactory.getLogger(JmsConnectionWrapper.class);

    private Connection connection;
    private Session session;
    private ActiveMQConnectionFactory connectionFactory;

    public JmsConnectionWrapper(final ActiveMQConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void close() {
        try {
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            logger.error("Error close connection.");
        }
    }
}
