package com.ccrm.util;

public class Exceptions {
    public static class NotFoundException extends RuntimeException { public NotFoundException(String m){ super(m);} }
    public static class ValidationException extends RuntimeException { public ValidationException(String m){ super(m);} }
    public static class BusinessRuleException extends RuntimeException { public BusinessRuleException(String m){ super(m);} }
}