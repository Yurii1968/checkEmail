import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CheckEmailTest {

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("validEmailProvider")
    void test_email_valid(String email) {
        assertTrue(CheckEmail.isValid(email));
    }

    @ParameterizedTest(name = "#{index} - Run test with email = {0}")
    @MethodSource("invalidEmailProvider")
    void test_email_invalid(String email) {
        assertFalse(CheckEmail.isValid(email));
    }

    // Valid email addresses
    static Stream<String> validEmailProvider() {
        return Stream.of(
                "hahah@haha.com",                // simple
                "hahah@hahah.co.uk",              // .co.uk, 2 tld
                "hhahah-2023@hahah.com",           // -
                "hahah.2023@hahah.com",           // .
                "hahah_2023@hahah.com",           // _
                "h@hahah.com");                   // local-part one letter
    }

    // Invalid email addresses
    static Stream<String> invalidEmailProvider() {
        return Stream.of(
                "hahah",                            // email need at least one @
                "hahah@2023@hahah.com",           // email doesn't allow more than one @
                ".hahah@hahah.com",               // local-part can't start with a dot .
                "hahah.@hahah.com",               // local-part can't end with a dot .
                "hahah@hahah.a",                  // domain tld min 2 chars
                "hahah@hahah..com",               // domain doesn't allow dot . appear consecutively
                "hahah@.com");                      // domain doesn't start with a dot .

    }

}
