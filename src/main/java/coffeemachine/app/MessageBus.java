package coffeemachine.app;

import java.util.ArrayList;
import java.util.Collection;

public final class MessageBus {

    private final Collection<Listener> listeners = new ArrayList<>();

    public void publish(Object message) {
        listeners.forEach(l -> l.listen(message));
    }

    void listen(Listener listener) {
        listeners.add(listener);
    }

    interface Listener {

        void listen(Object message);
    }
}
