package org.kd.ms.integration.custom;

import org.kd.ms.aop.Person;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;
import java.util.List;

public class CustomDataAdapter implements MessageSource {

    public List<Person> generatePersonData() {
        Person p1 = new Person("Ken", "id-str-121");
        Person p2 = new Person("Tommy", "id-str-122");
        Person p3 = new Person("Tom", "id-str-123");
        Person p4 = new Person("Dan", "id-str-124");

        return Arrays.asList(p1, p2, p3, p4);
    }

    @Override
    public Message receive() {
        List<Person> personList = generatePersonData();
        return MessageBuilder.withPayload(personList).build();
    }
}
