/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Admin
 */
public class Model extends Observable {

    ListaExpresiones list;
    public static String level = "\\Save File\\"; // Datos de prueba debe crearse en el directorio del proyecto
    public static String ruta = System.getProperty("user.dir").concat(level);
    public static String nombre = "resultados.txt";

    public Model() {
        this.list = new ListaExpresiones();
    }

    public Model(ListaExpresiones lista) {
        this.list = lista;
    }

    public ListaExpresiones getListaExpresiones() {
        return list;
    }

    public void setListaExpresiones(ListaExpresiones lista) {
        this.list = lista;
    }

    public void agregar(Expresion linea) {
        this.list.agregar(linea);
        setChanged();
        notifyObservers();
    }

    public Expresion getFormula(int pos) {
        return this.list.getExpresion(pos);
    }

    public LogicFunction describeSentence(String s) {
        LogicFunction lf = new LogicChain(s);
        return lf;
    }

    public void save(String contenido) {
        
    }

    //llamar a todos los metodos del script aquí
    public void addObserver(Observer o) {
        super.addObserver(o);
        // setChanged();
        //notifyObservers(null);
    }
}
