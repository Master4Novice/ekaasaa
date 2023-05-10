package com.learning.dwivna.ekaasaa.exceptions;

public class UserUpdateFailedException extends RuntimeException {

    public UserUpdateFailedException() {
        super("User Not Updated Exception");
    }
}
