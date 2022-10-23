/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Expresion;
import Model.Model;
import View.VistaPrincipal;
import java.io.File;
import java.io.IOException;
import java.util.Observer;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class Control {
    
    private Model model;
    VistaPrincipal v;
    
    public Control(Model model, VistaPrincipal v){
        this.model = model;
        this.v = v;
        this.model.addObserver(v);
        //this.v.setModel(model);
        this.v.setControl(this);
    }
    
    public Control(){
        this.model = new Model();
    }
    
    public void agregar(String linea){
        model.agregar(new Expresion(linea));
    }
    
    public void imprimir() {
        model.getListaExpresiones().imprimir();
    }
    
    public void readFile(File file) {
        try (Scanner entrada = new Scanner(file)) {
            while (entrada.hasNext()){
                String exp = entrada.nextLine();
                model.agregar(new Expresion(exp));
            }
        } catch (IOException ex) {
            
        }
    }
    
    public void observer(Observer o){
        this.model.addObserver(o);
    }
    
}
