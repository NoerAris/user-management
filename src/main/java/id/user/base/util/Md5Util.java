package id.user.base.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static Md5Util singleton = new Md5Util();

    public static Md5Util getInstance() {
        return singleton;
    }

    public static String encode(String raw) {
        return Md5Util.getInstance().get(raw);
    }

    public String get(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] msg = digest.digest(input.getBytes());
            BigInteger number = new BigInteger(1, msg);
            String text = number.toString(16);
            while (text.length() < 32) {
                text = "0" + text;
            }
            return text;
        } catch (NoSuchAlgorithmException j) {
            throw new RuntimeException(j);
        }
    }
}
