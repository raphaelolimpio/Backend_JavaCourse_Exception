package br.excepetion;

public class UserNotFoundExcepetion  extends RuntimeException {
    public UserNotFoundExcepetion(final String message) {
        super(message);
    }

}
