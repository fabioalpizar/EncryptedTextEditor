package main;

import Controller.Editor_Controller;
import View.Editor_View;

/**
 *
 * @author Andres Obando Alfaro
 */
public class main {
    public static void main(String[] args) {
        Editor_Controller controller;
        Editor_View view;
        
        view = new Editor_View();
        controller = new Editor_Controller(view);
    }
}
