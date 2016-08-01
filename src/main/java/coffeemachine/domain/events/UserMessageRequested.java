package coffeemachine.domain.events;

import lombok.Value;

@Value
public final class UserMessageRequested implements DrinkRequester {

    private final String id;
    private final String message;

    public UserMessageRequested(String id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public String toCommand() {
        return "T:" + message;
    }
}
