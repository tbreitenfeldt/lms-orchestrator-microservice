package com.smoothstack.lms.orchestratorservice.exception;

public class CriticalDatabaseErrorException extends RuntimeException {

    private static final long serialVersionUID = 679594131020188016L;

    public CriticalDatabaseErrorException(String message) {
        super(message);
    }

}
