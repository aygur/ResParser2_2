import com.naraikin.models.Counter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dmitrii on 14.02.17.
 */
class CounterTest {

    @Test
    void isError() {
        Counter counter = new Counter();
        assertFalse(counter.isError());
    }

    @Test
    void setError() {
        Counter cnt = new Counter();
        cnt.setError();
        assertTrue(cnt.isError());
    }

    @Test
    void addValue() {
        Counter cnt = new Counter();
        cnt.addValue(12);
        assertEquals(24, cnt.addValue(12) );
        assertNotEquals( 21, cnt.addValue(12) );
    }

    @Test
    void addValueAndPrint() {
        Counter cnt = new Counter();
        cnt.addValueAndPrint(12);
        assertEquals( 24, cnt.addValue(12) );
        assertNotEquals(21, cnt.addValue(12) );
    }

    @Test
    void getValue() {
        Counter cnt = new Counter();
        assertEquals(0, cnt.getValue());
        cnt.addValue(41);
        assertEquals(41, cnt.getValue());
    }

}