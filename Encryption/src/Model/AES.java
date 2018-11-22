package Model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Andres Obando Alfaro
 */
public class AES implements Crypter {
    
    @Override public String encrypt(String message, String key) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            
            for (int j = 0, k = 16; j < 8;) keyBytes[k++] = keyBytes[j++];
            
            final SecretKey secretkey = new SecretKeySpec(keyBytes, "AES");
            final IvParameterSpec iv = new IvParameterSpec(new byte[16]);
            final Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
            final byte[] plainTextBytes = message.getBytes("utf-8");
            final byte[] cipherText = cipher.doFinal(plainTextBytes);
            final String encodedCipherText = new sun.misc.BASE64Encoder().encode(cipherText);
            
            return encodedCipherText;
        }
        catch (NoSuchAlgorithmException ex) { return ""; }
        catch (UnsupportedEncodingException ex) { return ""; }
        catch (NoSuchPaddingException ex) { return ""; }
        catch (InvalidKeyException ex) { return ""; }
        catch (InvalidAlgorithmParameterException ex) { return ""; }
        catch (IllegalBlockSizeException ex) { return ""; }
        catch (BadPaddingException ex) { return ""; }
    }
    
    @Override public String decrypt(String message, String key) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            for (int j = 0, k = 16; j < 8;) keyBytes[k++] = keyBytes[j++];

            final SecretKey secretkey = new SecretKeySpec(keyBytes, "AES");
            final IvParameterSpec iv = new IvParameterSpec(new byte[16]);
            final Cipher decipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            decipher.init(Cipher.DECRYPT_MODE, secretkey, iv);
            byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(message);
            final byte[] plainText = decipher.doFinal(encData);
            return new String(plainText, "UTF-8");
        }
        catch (Exception e) {
            return "";
        }
    }

    @Override public int lenghtBetweenMessage() { return 15; }
}