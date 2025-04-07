/**
 * InvalidUserException class
 * Thrown when there is an error concerning a User object.
 * Either having incorrect username or user object than expected.
 *
 * @author Tanish Mudaliar, L09
 * @version Apr 2, 2025
 */

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}