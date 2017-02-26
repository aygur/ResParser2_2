import com.naraikin.models.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by dmitrii on 13.02.17.
 */
class ResourceTest {
    Resource resource;
    String trueFile = "files/file1.txt";
    String falseFile = "123";
    @BeforeEach
    void inti() {
        resource = new Resource(trueFile);
    }


    @Test
    void getResourceString()   {
        //feel good
        assertEquals(resource.getResourceString(), trueFile);
    }

    @Test
    void getBufferedReader() {
        //feel good
        try {
            assertNotNull(resource.getBufferedReader());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        //error
        assertThrows(IOException.class, () -> new Resource(falseFile).getBufferedReader());
    }

    @Test
    void validateFilePath() {
        assertTrue(Resource.validateFilePath(trueFile));
        assertFalse(Resource.validateFilePath(falseFile));
    }

}