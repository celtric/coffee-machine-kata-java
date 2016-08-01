package coffeemachine.app.handlers;

import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.events.UserMessageRequested;

public final class UserMessageRequestedHandler implements MessageHandler<UserMessageRequested> {

    private final DrinkMaker drinkMaker;

    public UserMessageRequestedHandler(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    @Override
    public void handle(UserMessageRequested message) {
        drinkMaker.command(message.toCommand());
    }
}
