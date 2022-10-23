/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editordeformulas.vista;

import editordeformulas.modelo.Formula;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rebeg
 */
public class TablaVerdadModel extends AbstractTableModel{
    Formula formula;   

    public TablaVerdadModel(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
    
    @Override
    public int getRowCount() {
        return formula.tablaDeVerdad().length;
    }

    @Override
    public int getColumnCount() {
        return formula.tablaDeVerdad()[0].length;
    }
    
    @Override    
    public String getColumnName(int columnIndex) {
        return formula.variablesFormula().get(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return formula.tablaDeVerdad()[rowIndex][columnIndex];
    }
}
