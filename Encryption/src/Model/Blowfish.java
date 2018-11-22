package Model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Andres Obando Alfaro
 */
public class Blowfish implements Crypter {

    @Override public String encrypt(String message, String key) {
        try {
            byte[] keyData = key.getBytes("UTF-8");
            SecretKeySpec secretkey = new SecretKeySpec(keyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey);
            byte[] hasil = cipher.doFinal(message.getBytes("UTF-8"));
            
            return new BASE64Encoder().encode(hasil);
        }
        catch (NoSuchAlgorithmException ex) { return ""; }
        catch (NoSuchPaddingException ex) { return ""; }
        catch (InvalidKeyException ex) { return ""; }
        catch (IllegalBlockSizeException ex) { return ""; }
        catch (BadPaddingException ex) { return ""; }
        catch (UnsupportedEncodingException ex) { return ""; }
    }

    @Override public String decrypt(String message, String key) {
        try {
            byte[] keyData = key.getBytes();
            SecretKeySpec secretkey = new SecretKeySpec(keyData, "Blowfish");
            Cipher cipher = Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, secretkey);
            byte[] hasil = cipher.doFinal(new BASE64Decoder().decodeBuffer(message));
            return new String(hasil);
        }
        catch (Exception e) {
            return RandomMessage.getRandomMessage(message.length());
        }
    }

    @Override
    public int messageInterval() {
        return 0;
    }

    @Override
    public int cryptInterval() {
        return 0;
    }
}
