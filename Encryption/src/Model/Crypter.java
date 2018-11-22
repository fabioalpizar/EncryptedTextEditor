package Model;

import java.util.HashMap;

/**
 *
 * @author Andres Obando Alfaro
 */
public interface Crypter {
    public String encrypt(String message, String key);
    public String decrypt(String message, String key);
    
    public HashMap<String, String> Blocks(char[] message, char[] crypted);
}
