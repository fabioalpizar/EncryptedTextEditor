package Model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import static javax.swing.Spring.sum;

/**
 *
 * @author Andres Obando Alfaro
 */
public class TripleDES implements Crypter {
    
    @Override public String encrypt(String message, String key) {
        try {
            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(key.getBytes("utf-8"));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            
            for (int j = 0, k = 16; j < 8;) keyBytes[k++] = keyBytes[j++];
            
            final SecretKey secretkey = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
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

            final SecretKey secretkey = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, secretkey, iv);
            byte[] encData = new sun.misc.BASE64Decoder().decodeBuffer(message);
            final byte[] plainText = decipher.doFinal(encData);
            return new String(plainText, "UTF-8");
        }
        catch (Exception e) {
            return RandomMessage.getRandomMessage(message.length());
        }
    }
    
    @Override public HashMap<String, String> Blocks(char[] message, char[] crypted) {
        HashMap<String, String> Pairs = new HashMap<>();
        
        System.out.println(message.length / 7);
        System.out.println(crypted.length / 12);
        
        return Pairs;
    }
}
