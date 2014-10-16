package com.videolive.util;

public class BlowfishUtil {
	 private static Blowfish cipher = null;
	 /**
     * 单例模式，对密码解密
     */
    public static synchronized Blowfish getCipher(String passwordKey) {
        if (cipher != null) {
            return cipher;
        }
        // Get the password key, stored as a database property. Obviously,
        // protecting your database is critical for making the
        // encryption fully secure.
        String keyString;
        try {
            keyString = passwordKey;
            // Check to make sure that setting the property worked. It won't work,
            // for example, when in setup mode.
            if (!keyString.equals(passwordKey)) {
                return null;
            }
            cipher = new Blowfish(keyString);
        }
        catch (Exception e) {
           // Log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return cipher;
    }

}
