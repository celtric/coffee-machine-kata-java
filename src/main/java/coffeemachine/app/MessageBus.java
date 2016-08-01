package coffeemachine.app;

import java.util.ArrayList;
import java.util.Collection;

public final class MessageBus {

    private static final Collection<Listener> listeners = new ArrayList<>();

    interface Listener {

        void listen(Object message);
    }

    public static void publish(Object message) {
        listeners.forEach(l -> l.listen(message));
    }

    static void listen(Listener listener) {
        listeners.add(listener);
    }
}
