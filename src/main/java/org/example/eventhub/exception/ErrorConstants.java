package org.example.eventhub.exception;

public final class ErrorConstants {
    private ErrorConstants() {}

    // NOT FOUND EXCEPTIONS
    public static final String USER_BY_ID_NOT_FOUND = "User not found with ID: %s";
    public static final String REQUEST_BOOKING_BY_ID_NOT_FOUND = "Request booking with ID: %s not found";
    public static final String COMPLAINT_BY_ID_NOT_FOUND = "Complaint with ID: %s not found";
    public static final String CONTRACTOR_BY_ID_NOT_FOUND = "Contractor with ID: %s not found";
    public static final String CONTRACTOR_EVENT_BY_ID_NOT_FOUND = "Contractor event with ID: %s not found";
    public static final String CONTRACTOR_EVENT_BY_CONTRACTOR_ID_NOT_FOUND = "Contractor event with ID: %s not found";
    public static final String EVENT_BY_ID_NOT_FOUND = "Event with ID: %s not found";
    public static final String EVENT_BY_USER_ID_NOT_FOUND = "Event with userId: %s not found";
    public static final String FEEDBACK_BY_ID_NOT_FOUND = "Feedback with ID: %s not found";
    public static final String PRICELIST_BY_ID_NOT_FOUND = "PriceList with ID: %s not found";

    //User exceptions
    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXIST = "userId with this email already exists";
    public static final String USER_WITH_THIS_USERNAME_ALREADY_EXIST = "userId with this username already exists";
    public static final String INVALID_REFRESH_TOKEN = "Invalid refresh token";
    public static final String INVALID_USERNAME_IN_LOGIN_TOKEN= "Invalid username in login token";
    public static final String INVALID_PASSWORD_FOR_USERNAME_IN_LOGIN_TOKEN = "Invalid password for username in login token";
}