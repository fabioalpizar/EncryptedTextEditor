/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryptedtexteditor;

/**
 *
 * @author Pumkin
 */
public class Encryption {
    
    private String encryptionType;
    private String seed;

    public Encryption(String encryptionType, String seed) {
        this.encryptionType = encryptionType;
        this.seed = seed;
    }
    
    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }
    
    //Encryption Algorithm
    public String encryptTxt(String txt){
        String encrypted = "";
        switch (this.getEncryptionType()){
            case "Des" : 
                encrypted = DesEnc(txt, this.seed);
            case "AES" :
                encrypted = AesEnc(txt, this.seed);     
            case "Blowfish" :
                encrypted = BlowfishEnc(txt, this.seed);
            default :
                encrypted = txt;
        }
        return encrypted;
    }
 
    private String DesEnc(String txt, String seed){
        //Triple DES encryption algorithm here
        return txt;
    }
    
    private String AesEnc(String txt, String seed){
        //AES encryption algorithm here
        return txt;
    }
    
    private String BlowfishEnc(String txt, String seed){
        //Blowfish encryption algorithm here
        return txt;
    }
    
    //Encryption Algorithm
    public String decryptTxt(String txt){
    String encrypted = "";
    switch (this.getEncryptionType()){
        case "Des" : 
            encrypted = DesDec(txt, this.seed);
        case "AES" :
            encrypted = AesDec(txt, this.seed);     
        case "Blowfish" :
            encrypted = BlowfishDec(txt, this.seed);
        default :
            encrypted = txt;
    }
    return encrypted;
    }
 
    private String DesDec(String txt, String seed){
        //Triple DES decryption algorithm here
        return txt;
    }
    
    private String AesDec(String txt, String seed){
        //AES decryption algorithm here
        return txt;
    }
    
    private String BlowfishDec(String txt, String seed){
        //Blowfish decryption algorithm here
        return txt;
    }
    
}
