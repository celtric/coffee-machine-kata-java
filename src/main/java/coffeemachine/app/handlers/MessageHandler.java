package coffeemachine.app.handlers;

public interface MessageHandler<T> {

    void handle(T message);
}
