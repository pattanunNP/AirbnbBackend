package org.armzbot.exception;

import java.io.Serial;

public class AcessDeniedException extends RuntimeException
{
    @Serial
    private static final long serialVersionUID = 1L;


    private String message;

}
