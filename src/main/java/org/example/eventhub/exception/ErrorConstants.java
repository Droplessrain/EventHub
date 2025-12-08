package org.example.eventhub.exception;

public final class ErrorConstants {
    private ErrorConstants() {}

    public static final String USER_BY_ID_NOT_FOUND = "User not found with ID: %s";
    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXIST = "user with this email already exists";
    public static final String USER_WITH_THIS_USERNAME_ALREADY_EXIST = "user with this username already exists";
    public static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";
    public static final String INVALID_USERNAME_IN_LOGIN_TOKEN= "Invalid username in login token";
    public static final String INVALID_PASSWORD_FOR_USERNAME_IN_LOGIN_TOKEN = "Invalid password for username in login token";
}