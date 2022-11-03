/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Control;
import Model.Expresion;
import Model.LogicChain;
import Model.LogicFunction;
import Model.Model;
import static Model.Model.nombre;
import static Model.Model.ruta;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class vistaExpresion extends javax.swing.JFrame {
    
    private Control control;
    Model model;
    Expresion exp;
    String nuevoExp = "nada";
    BufferedWriter bw = null;
    FileWriter fw = null;
    /**
     * Creates new form vistaExpresion
     * @param title
     * @param control
     * @param exp
     */
    public vistaExpresion(String title, Control control, Expresion exp) {
        super(title);
        this.control = control;   
        this.exp = exp;
        initComponents();
        this.renderDatos();
        this.setTitle("Proyecto #1 Paradigmas");
        this.getContentPane().setBackground(Color.LIGHT_GRAY);
        this.setLocationRelativeTo(null);
        this.setSize(600, 700);
        //jTextArea1_TableTruth.setText("|p | q | r | f(p q r)");
        //jTextArea2_Canonica.setText("Canónicas Disyuntivas: (p∧q∧r)∨(p∧q∧¬r)∨(p∧¬q∧r)∨(¬p∧q∧r)");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Expresion_jTextField = new javax.swing.JTextField();
        Analizar_jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1_TableTruth = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2_Canonica = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1_Guardar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Expresion_jTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Expresion_jTextFieldActionPerformed(evt);
            }
        });

        Analizar_jButton1.setText("Analizar");
        Analizar_jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Analizar_jButton1ActionPerformed(evt);
            }
        });

        jTextArea1_TableTruth.setColumns(20);
        jTextArea1_TableTruth.setRows(5);
        jScrollPane1.setViewportView(jTextArea1_TableTruth);

        jTextArea2_Canonica.setColumns(20);
        jTextArea2_Canonica.setRows(5);
        jScrollPane2.setViewportView(jTextArea2_Canonica);

        jLabel1.setText("Canónicas");

        jLabel2.setText("Tabla de Verdad");

        jButton1_Guardar.setText("Guardar");
        jButton1_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1_GuardarActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Guarde y  Edite expresiones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Expresion_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(23, 23, 23)
                                    .addComponent(jButton1_Guardar)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Analizar_jButton1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(jLabel1))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Expresion_jTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Analizar_jButton1)
                    .addComponent(jButton1_Guardar))
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Expresion_jTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Expresion_jTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Expresion_jTextFieldActionPerformed

    private void Analizar_jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Analizar_jButton1ActionPerformed
        nuevoExp = Expresion_jTextField.getText();
        LogicFunction lf = new LogicChain(nuevoExp);
        //LogicFunction s = model.describeSentence(exp.getExpresion());
        jTextArea1_TableTruth.setText(lf.getTruthTableRepresentation());
        jTextArea2_Canonica.setText("Canónicas Disyuntivas: " + lf.expressAsSumOfMinterms() + "\n"+ "Canónicas Conjuntivas: " + lf.expressAsProductOfMaxterms());
    }//GEN-LAST:event_Analizar_jButton1ActionPerformed

    private void jButton1_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1_GuardarActionPerformed
        //System.out.println(nuevoExp);   
        try {
        // flag true, indica adjuntar información al archivo.
        fw = new FileWriter(ruta + nombre, true);
        bw = new BufferedWriter(fw);
        bw.write(nuevoExp+"\n");
        System.out.println("información agregada!");
        JOptionPane.showMessageDialog(this, "Información agregada! en carpeta Save File del proyecto");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    }//GEN-LAST:event_jButton1_GuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaExpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaExpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaExpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaExpresion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaExpresion("Expresion", new Control(), new Expresion()).setVisible(true);
            }
        });
    }
    
    public void init() {
        setVisible(true);
    }

    public void renderDatos() {
        this.nuevoExp = exp.getExpresion();
        this.Expresion_jTextField.setText(nuevoExp);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Analizar_jButton1;
    private javax.swing.JTextField Expresion_jTextField;
    private javax.swing.JButton jButton1_Guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1_TableTruth;
    private javax.swing.JTextArea jTextArea2_Canonica;
    // End of variables declaration//GEN-END:variables
}
