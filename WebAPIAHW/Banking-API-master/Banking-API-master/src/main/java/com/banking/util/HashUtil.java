package com.banking.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hannah ORourke
 */
public class HashUtil {
    private static MessageDigest messageDigest = null;

    public static String sha256(String message) {
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = mDigest.digest(message.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException ex){
            System.err.print(ex);
        }
        return null;
    }
}
