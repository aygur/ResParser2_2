import com.naraikin.models.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dmitrii on 13.02.17.
 */
class ParserTest {

    @DisplayName("Testing parser method")
    @org.junit.jupiter.api.Test
    void getPositiveEvenSum() {
        //feel good
        Assertions.assertEquals(Parser.getPositiveEvenSum("12 "), 12);
        assertEquals(Parser.getPositiveEvenSum("12 12 -1"), 24);
        assertEquals(Parser.getPositiveEvenSum("0 -7 -1"), 0);

        //error
        assertThrows(NumberFormatException.class, () -> Parser.getPositiveEvenSum("0* -7 -1"));
        assertEquals(Parser.getPositiveEvenSum(null), 0);
        assertEquals(Parser.getPositiveEvenSum(""), 0);
    }

}