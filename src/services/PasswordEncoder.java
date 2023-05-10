package services;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author don7a
 */
public class PasswordEncoder {

    public static String encode(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public static boolean matches(String password, String hash) {
    password = "$2a$" + password.substring(4);
    return BCrypt.checkpw(password, hash);
}

}