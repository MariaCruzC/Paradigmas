/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class Expresion {
    
    private String expresion;

    public Expresion(String expresion) {
        this.expresion = expresion;
    }
    
    public Expresion() {
        this.expresion = " ";
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
}
