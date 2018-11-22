package Model;

import java.util.ArrayList;

/**
 *
 * @author Andres Obando Alfaro
 */
public class Malespin implements Crypter {

    @Override public String encrypt(String message, String key) {
        String msg = "";
        
        for (char letter : message.toCharArray())
            msg += changeLetter(letter);
        return msg;
    }

    @Override public String decrypt(String message, String key) {
        return encrypt(message, "");
    }
    
    public static String changeLetter(char letter) {
        switch (letter) {
            case 'A': return "E";
            case 'a': return "e";
            case 'E': return "A";
            case 'e': return "e";
            case 'B': return "T";
            case 'b': return "t";
            case 'T': return "B";
            case 't': return "b";
            case 'F': return "G";
            case 'f': return "g";
            case 'G': return "F";
            case 'g': return "f";
            case 'I': return "O";
            case 'i': return "o";
            case 'O': return "I";
            case 'o': return "i";
            case 'M': return "P";
            case 'm': return "p";
            case 'P': return "M";
            case 'p': return "m";
            default: return Character.toString(letter);
        }
    }

    @Override
    public int lenghtBetweenMessage() { return 1; }
}
