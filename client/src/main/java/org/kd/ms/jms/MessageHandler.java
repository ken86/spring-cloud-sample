package org.kd.ms.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

public class MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);

    public Message<?> handle(final Message<?> message) {
        logger.info("#######Handling message....");
        Object obj = message.getPayload();
        if(message.getPayload() instanceof String) {
            logger.info("String obj");
        }
        logger.info(obj.toString());
        return message;
    }

}
