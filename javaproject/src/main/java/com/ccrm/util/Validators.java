package com.ccrm.util;

import java.util.regex.Pattern;

public final class Validators {
    private static final Pattern EMAIL = Pattern.compile("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    private Validators(){}
    public static void require(boolean cond, String msg){ if(!cond) throw new Exceptions.ValidationException(msg); }
    public static void email(String s){ require(EMAIL.matcher(s).matches(), "Invalid email: "+s); }
    public static void nonEmpty(String s, String f){ require(s!=null && !s.isBlank(), f+" cannot be empty"); }
}