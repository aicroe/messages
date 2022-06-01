package org.example.messagesservice.core.services.context;

import java.util.concurrent.Callable;

public class ContextExceptionHandler {

    public void catchAndLog(Callable<?> callback) {
        try {
            callback.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
