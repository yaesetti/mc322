package game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class InputManagerTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private final PrintStream originalSystemErr = System.err;
    private ByteArrayInputStream testIn;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @AfterEach
    void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        System.setErr(originalSystemErr);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testReadStringValid() {
        provideInput("Helena\n");

        String result = InputManager.readString("Input a string: ");
        assertEquals("Helena", result);
    }

    @Test
    public void testReadStringInvalid() {
        System.setErr(new PrintStream(errContent));

        // Provides a valid input "Helena\n" after the
        // invalid input "\n" in order to stop the while
        // loop inside readString() after the test
        provideInput("\nHelena\n");

        String result = InputManager.readString("Input a string: ");
        
        // readString() must ignore the invalid input
        // and return the valid input that was inserted after
        assertEquals("Helena", result);
        
        // Checks if the "Invalid Input" message was displayed
        // in the Error Stream after trying to provide an invalid input
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Invalid Input: Input must not be empty"));
    }

    @Test
    public void testReadBooleanValid() {
        // Provides 4 variations of valid inputs.
        // The first two must return true and
        // the last two must return false
        boolean result; 

        provideInput("y\n");
        result = InputManager.readBoolean("Question");
        assertTrue(result);

        provideInput("YeS\n");
        result = InputManager.readBoolean("Question");
        assertTrue(result);

        provideInput("n\n");
        result = InputManager.readBoolean("Question");
        assertFalse(result);

        provideInput("nO\n");
        result = InputManager.readBoolean("Question");
        assertFalse(result);

    }

    @Test
    public void testReadBooleanInvalidEmpty() {
        System.setErr(new PrintStream(errContent));

        // Provides a valid input "y\n" after the
        // invalid input "\n" in order to stop the while
        // loop inside readString() after the test
        provideInput("\ny\n");

        boolean result = InputManager.readBoolean("Question");
        
        // readBoolean() must ignore the invalid input
        // and return the boolean that represents the
        // valid input provided after
        assertTrue(result);
        
        // Checks if the "Invalid Input: Input must no be empty"
        // message was displayed in the Error Stream after
        // trying to provide an empty input
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Invalid Input: Input must not be empty"));
    }

    @Test
    public void testReadBooleanInvalidNotYN() {
        System.setErr(new PrintStream(errContent));

        // Provides a valid input "y\n" after the
        // invalid input "sim\n" in order to stop the while
        // loop inside readString() after the test
        provideInput("sim\ny\n\n");

        boolean result = InputManager.readBoolean("Question");
        
        // readBoolean() must ignore the invalid input
        // and return the boolean that represents the
        // valid input provided after
        assertTrue(result);
        
        // Checks if the "Invalid Input: Input must be 'y' or 'n'"
        // message was displayed in the Error Stream after
        // trying to provide an invalid input
        String errorOutput = errContent.toString();
        assertTrue(errorOutput.contains("Invalid Input: Input must be 'y' or 'n'"));
    }
}
