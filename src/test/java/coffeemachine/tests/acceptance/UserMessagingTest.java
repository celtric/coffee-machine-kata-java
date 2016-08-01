package coffeemachine.tests.acceptance;

import coffeemachine.adapters.InMemoryReportingRepository;
import coffeemachine.app.App;
import coffeemachine.app.MessageBus;
import coffeemachine.domain.ReportingRepository;
import coffeemachine.domain.events.UserMessageRequested;
import coffeemachine.tests.utils.FakeDrinkMaker;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class UserMessagingTest {

    private final FakeDrinkMaker drinkMaker = new FakeDrinkMaker();
    private final ReportingRepository reportingRepository = new InMemoryReportingRepository();
    private final MessageBus messageBus = new MessageBus();
    private final App app = App.start(drinkMaker, reportingRepository, messageBus);

    @Test
    public void a_message_can_be_sent_to_a_customer() {
        publish(new UserMessageRequested("AN-ID", "Hello world"));

        assertMessagePrinted("Hello world");
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private void publish(Object message) {
        messageBus.publish(message);
    }

    private void assertMessagePrinted(String message) {
        assertTrue(drinkMaker.hasMessageBeenPrinted(message));
    }
}
