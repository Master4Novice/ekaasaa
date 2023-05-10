package com.learning.dwivna.ekaasaa.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("User Not Found Exception");
    }
}
