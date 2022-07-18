package com.antoniocmoura.pixbank.application;

/* Patern command */

import com.antoniocmoura.pixbank.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UseCase<IN, OUT> {

   public abstract OUT execute(IN anIn);

}
