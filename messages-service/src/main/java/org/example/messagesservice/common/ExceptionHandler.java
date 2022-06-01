package org.example.messagesservice.common;

import java.util.concurrent.Callable;

public abstract class ExceptionHandler<E extends Throwable> {

    public <T> T runAndHandle(Callable<T> callback) throws E {
        try {
            return callback.call();
        } catch (Exception e) {
            throw createFrom(e);
        }
    }

    protected abstract E createFrom(Exception e);
}
