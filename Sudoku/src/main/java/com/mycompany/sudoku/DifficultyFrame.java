package com.mycompany.sudoku;

import java.util.logging.Level;
import java.util.logging.Logger;


public class DifficultyFrame extends javax.swing.JFrame {

    SudokuController controller = new SudokuController();
        SudokuViewAdapter viewer = new SudokuViewAdapter(controller);
    public DifficultyFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        meduimButton = new javax.swing.JButton();
        EasyButton = new javax.swing.JButton();
        hardButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        meduimButton.setText("MEDIUM");
        meduimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meduimButtonActionPerformed(evt);
            }
        });

        EasyButton.setText("EASY");
        EasyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EasyButtonActionPerformed(evt);
            }
        });

        hardButton.setText("HARD");
        hardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(EasyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meduimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addComponent(EasyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(meduimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(hardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void meduimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meduimButtonActionPerformed
        try {
            viewer.getGame('M');
        } catch (NotFoundException ex) {
            Logger.getLogger(DifficultyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_meduimButtonActionPerformed

    private void EasyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EasyButtonActionPerformed
        try {
            viewer.getGame('E');
        } catch (NotFoundException ex) {
            Logger.getLogger(DifficultyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EasyButtonActionPerformed

    private void hardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardButtonActionPerformed
       try {
            viewer.getGame('H');
        } catch (NotFoundException ex) {
            Logger.getLogger(DifficultyFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_hardButtonActionPerformed
 

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DifficultyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EasyButton;
    private javax.swing.JButton hardButton;
    private javax.swing.JButton meduimButton;
    // End of variables declaration//GEN-END:variables
}
