package com.example.proshore.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a generic response object.
 * <p>
 * This class is used to encapsulate the result of an operation, including a response body,
 * a message, and a success indicator.
 *
 * @author Dilip Babu Acharya
 * @version 1.0
 * @since 2023-09-15
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    /**
     * The body of the response, which can contain any object type.
     */
    private Object body;

    /**
     * A message describing the result of the operation or additional information.
     */
    private String message;

    /**
     * A flag indicating whether the operation was successful (true) or not (false).
     */
    private boolean success;
}
