package Day10;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    private static Microchip m1;
    private static Microchip m2;
    private static Microchip m3;
    private static Microchip m4;

    @BeforeClass
    public static void setUp() {
        m1 = new Microchip(1);
        m2 = new Microchip(2);
        m3 = new Microchip(3);
        m4 = new Microchip(4);
    }

    @Test
    public void shouldCreateObjectProperly() {
        new Bot(1);
    }

    @Test
    public void shouldAddValuesProperly() {
        Bot b = new Bot(299);
        b.addValue(m1);
        assertFalse(b.hasTwoMicroChips());
        b.addValue(m2);
        assertEquals(1, b.getLower().getID());
        assertEquals(2, b.getHigher().getID());
        assertTrue(b.hasTwoMicroChips());
    }

    @Test
    public void shouldAddValuesProperly2() {
        Bot b = new Bot(3);
        b.addValue(m4);
        b.addValue(m3);
        assertEquals(3, b.getLower().getID());
        assertEquals(4, b.getHigher().getID());
        assertTrue(b.hasTwoMicroChips());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntCreateBotObject() {
        new Bot(100);
        new Bot(100);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldntCreateBotObject2() {
        new Bot(-1);
    }
}
