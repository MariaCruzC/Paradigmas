/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ListaExpresiones {

    List<Expresion> listaExp = new ArrayList<Expresion>();

    public List<Expresion> getListaExp() {
        return listaExp;
    }

    public void setListaExp(ArrayList<Expresion> listaExp) {
        this.listaExp = listaExp;
    }

    public void agregar(Expresion exp) {
        listaExp.add(exp);
    }
    
    public Expresion getExpresion(int pos){
        return listaExp.get(pos);
    }

    public void imprimir() {
        Iterator<Expresion> i = listaExp.iterator();
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }

}
