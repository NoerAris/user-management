package id.user.base.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5PassUtil implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        String encoded;
        try {
            encoded = Md5Util.encode(charSequence.toString());
        } catch (RuntimeException k) {
            encoded = "";
        }
        return encoded;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return this.encode(charSequence.toString()).equals(s);
    }
}
