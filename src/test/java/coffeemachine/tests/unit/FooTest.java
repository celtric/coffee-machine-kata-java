package coffeemachine.tests.unit;

import coffeemachine.Foo;
import org.junit.Test;

import static org.junit.Assert.*;

public final class FooTest {

    @Test
    public void returnsTrue() {
        assertTrue(new Foo().returnTrue());
    }
}
