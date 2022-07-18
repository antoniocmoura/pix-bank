package com.antoniocmoura.pixbank.domain.exceptions;

import com.antoniocmoura.pixbank.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}
