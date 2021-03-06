package coffeemachine.app;

import coffeemachine.app.MessageBus.Listener;
import coffeemachine.app.handlers.MessageHandler;

final class BusMessageHandler<T> implements Listener {

    private final MessageHandler<T> handler;

    BusMessageHandler(MessageHandler<T> handler) {
        this.handler = handler;
    }

    @Override
    public void listen(Object message) {
        // TODO: implement better method of dispatching
        try {
            //noinspection unchecked
            handler.handle((T) message);
        } catch (ClassCastException ignored) {}
    }
}
