package ua.nure.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Authorization {
    private final String testLogin;
    private final String testHashedPassword;
    public Authorization() {
        testLogin = "sdy";
        testHashedPassword = "$2a$12$qayTX1pDYDmVyJp1M0yMnu27JGXmVFQIILgKnGEvkCXIfCW4R/Z0.";
    }

    public boolean authorized(String login, String password) {
        return testLogin.equals(login)
                && BCrypt.verifyer().verify(password.getBytes(), testHashedPassword.getBytes()).verified;
    }

    public String getLogin() {
        return testLogin;
    }
}
