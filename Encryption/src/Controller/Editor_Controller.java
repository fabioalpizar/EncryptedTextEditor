package Controller;

import Model.AES;
import Model.Blowfish;
import Model.Crypter;
import Model.Malespin;
import Model.RandomMessage;
import Model.TripleDES;
import View.Editor_View;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Andres Obando Alfaro
 */
public class Editor_Controller implements ActionListener {
    
    private Crypter model;
    private Editor_View view;
    
    // Constructor
    public Editor_Controller(Editor_View view) {
        this.view = view;
        
        // RadioButtons
        view.Algorithms_ButtonGroup.add(view.TDES_RButton);
        view.Algorithms_ButtonGroup.add(view.AES_RButton);
        view.Algorithms_ButtonGroup.add(view.blowfish_RButton);
        view.Algorithms_ButtonGroup.add(view.malespin_RButton);
        
        // Buttons
        this.view.encrypt_Button.addActionListener(this);
        this.view.decrypt_Button.addActionListener(this);
        this.view.save_Button.addActionListener(this);
        this.view.load_Button.addActionListener(this);
        this.view.partial_Button.addActionListener(this);
        this.view.partialDecrypt_Button.addActionListener(this);
        
        // For Display
        view.setLocationRelativeTo(view);
        view.setVisible(true);
    }
    
    // ActionListener
    @Override public void actionPerformed(ActionEvent ae) {
        selectMethod();
        
        switch (ae.getActionCommand()) {
            case "Steganography":
                break;
            case "Encrypt":
                Encrypt();
                break;
            case "Decrypt":
                Decrypt();
                break;
            case "Load":
                Load();
                break;
            case "Save":
                Save();
                break;
            case "P. Encrypt":
                PartialEncryption();
                break;
            case "P. Decrypt":
                PartialDecryption();
                break;
        }
    }
    
    // Options
    public void Steganography() {
        
    }
    public void Encrypt() {
        // Encrypt Message
        String message = view.editor_TextArea.getText();
        String key = view.key_TextField.getText();
        String crypted = model.encrypt(message, key);
        
        // Display Message
        view.partial_TextArea.setText(crypted);
    }
    public void Decrypt() {
        try {
            String message = view.partial_TextArea.getText();
            String key = view.key_TextField.getText();
            String decrypted = model.decrypt(message, key);
            
            view.editor_TextArea.setText(decrypted);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "ERROR: Cannot Display the message");
        }
    }
    public void selectMethod() {
        if (view.TDES_RButton.isSelected()) model = new TripleDES();
        else if (view.AES_RButton.isSelected()) model = new AES();
        else model = view.blowfish_RButton.isSelected() ? new Blowfish() : new Malespin();
    }
    public void Load() {
        // Create an object of JFileChooser class 
        JFileChooser j = new JFileChooser("f:");
        
        // Invoke the showsOpenDialog function to show the save dialog
        int r = j.showOpenDialog(null);
        
        if (r == JFileChooser.APPROVE_OPTION) {
            // Set the label to the path of the selected directory
            File fi = new File(j.getSelectedFile().getAbsolutePath());
            
            try {
                String line = "", message = "";
                
                FileReader fr = new FileReader(fi);
                BufferedReader br = new BufferedReader(fr);
                message = br.readLine();
                
                while ((line = br.readLine()) != null)
                    message = message + "\n" + line;
                
                view.partial_TextArea.setText(message);
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
        // If the user cancelled the operation 
        else
            JOptionPane.showMessageDialog(view, "the user cancelled the operation");
    }
    public void Save() {
        // Create an object of JFileChooser class
        JFileChooser j = new JFileChooser("f:"); 
        
        // Invoke the showsSaveDialog function to show the save dialog 
        int r = j.showSaveDialog(null);
        
        if (r == JFileChooser.APPROVE_OPTION) {
            //Create file
            File f = new File(j.getSelectedFile().getAbsolutePath());
            
            try {
                // Create a file writer
                FileWriter wr = new FileWriter(f, false);
                BufferedWriter w = new BufferedWriter(wr);
                
                //Encryp text
                String message = view.editor_TextArea.getText();
                String key = view.key_TextField.getText();
                String encryptedTxt = model.encrypt(message, key);
                
                // Write
                w.write(encryptedTxt);

                //Close
                w.flush();
                w.close();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage());
            }
        }
        // If the user cancelled the operation 
        else
            JOptionPane.showMessageDialog(view, "Operation was cancelled.");
    }
    private void PartialEncryption() {
        ArrayList<Integer> lines = new ArrayList<>();
        
        try {
            for (String line : view.partialLines_TextField.getText().split("-"))
                lines.add(Integer.parseInt(line));
        }
        catch (Exception e) {
            return;
        }
        
        String result = "";
        String key = view.key_TextField.getText();
        int index = 1;
        
        for (String line : view.editor_TextArea.getText().split("\n")) {
            result += lines.contains(index++) ? model.encrypt(line, key) : line;
            result += "\n";
        }
        
        view.partial_TextArea.setText(result);
    }
    private void PartialDecryption() {
        ArrayList<Integer> octets = new ArrayList<Integer>();
        
        for (String octet : view.partialLines_TextField.getText().split("-"))
            octets.add(Integer.parseInt(octet));
        
        String message = view.partial_TextArea.getText();
        String key = view.key_TextField.getText();
        String decryptedMessage = model.decrypt(message, key);
        String partialDecrypted = "";
        
        char[] messageChars = message.toCharArray();
        char[] decrypted = decryptedMessage.toCharArray();
        
        HashMap<String, String> hello = model.Blocks(decrypted, messageChars);
        
        for (String hkey : hello.keySet())
            System.out.println(hkey + ":   " + hello.get(hkey));
    }
}