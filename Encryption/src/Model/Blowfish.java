package Model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
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
        catch (NoSuchAlgorithmException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (UnsupportedEncodingException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (NoSuchPaddingException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (InvalidKeyException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (IllegalBlockSizeException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (BadPaddingException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        catch (IOException ex) { Logger.getLogger(TripleDES.class.getName()).log(Level.SEVERE, null, ex); }
        return "";
    }
}
