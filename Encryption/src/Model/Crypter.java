package Model;

/**
 *
 * @author Andres Obando Alfaro
 */
public interface Crypter {
    public String encrypt(String message, String key);
    public String decrypt(String message, String key);
}
