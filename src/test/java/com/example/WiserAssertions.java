package com.example;


import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WiserAssertions {

    private final List<WiserMessage> messages;

    private WiserAssertions(List<WiserMessage> messages) {
        this.messages = messages;
    }

    public static WiserAssertions assertReceivedMessage(Wiser wiser) {
        return new WiserAssertions(wiser.getMessages());
    }

    private static <T> T unchecked(ThrowingSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public WiserAssertions from(String from) {
        findFirstAndAssert(WiserMessage::getEnvelopeSender,
                sender -> assertEquals(from, sender));
        return this;
    }

    public WiserAssertions to(String to) {
        findFirstAndAssert(WiserMessage::getEnvelopeReceiver,
                receiver -> assertEquals(to, receiver));
        return this;
    }

    public WiserAssertions withSubject(String subject) {
        findFirstAndAssert(m -> unchecked(getMimeMessage(m)::getSubject),
                article -> assertEquals(subject, article));
        return this;
    }

    public WiserAssertions withContent(String content) {
        findFirstAndAssert(m -> unchecked(getMimeMessage(m)::getContent),
                text -> assertTrue(text.toString().contains(content)));
        return this;
    }

    private void findFirstAndAssert(Function<WiserMessage, ? super Object> function, Consumer<? super Object> consumer) {
        messages.stream()
                .findFirst()
                .map(function)
                .ifPresent(consumer);
    }

    private MimeMessage getMimeMessage(WiserMessage wiserMessage) {
        return unchecked(wiserMessage::getMimeMessage);
    }

    interface ThrowingSupplier<T> {
        T get() throws Throwable;
    }
}
