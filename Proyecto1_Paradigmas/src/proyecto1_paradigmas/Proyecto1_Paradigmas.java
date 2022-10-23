/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_paradigmas;

import Controller.Control;
import Model.Model;
import View.VistaPrincipal;

/**
 *
 * @author Admin
 */
public class Proyecto1_Paradigmas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Model m = new Model();
        VistaPrincipal v = new VistaPrincipal();
        Control c = new Control(m, v);
        v.setVisible(true);
    }
}

