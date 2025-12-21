package com.mycompany.sudoku;

import javax.swing.*;

public class SudokuGUI extends javax.swing.JFrame {

    public SudokuGUI() {
        initComponents();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newButton = new javax.swing.JButton();
        incompleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        newButton.setText("NEW GAME");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        incompleteButton.setText("COMPLETE GAME");
        incompleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                incompleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(incompleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(incompleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(newButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        SudokuController controller = SudokuController.getInstance();
        Catalog catalog = controller.getCatalog();

        if (catalog.hasUnfinishedGame) {
            try {
                int[][] board = controller.getGame('I');
                new GameFrame(board, "INCOMPLETE").setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No incomplete game found");
            }
        } else if (catalog.hasAllDifficulties) {
            new DifficultyFrame().setVisible(true);
            this.dispose();
        } else {
            new LoadSolvedGameFrame().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void incompleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_incompleteButtonActionPerformed
        try {
            SudokuController controller = SudokuController.getInstance();
            int[][] board = controller.getGame('I');
            new GameFrame(board, "INCOMPLETE").setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No incomplete game found");
        }
    }//GEN-LAST:event_incompleteButtonActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SudokuGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton incompleteButton;
    private javax.swing.JButton newButton;
    // End of variables declaration//GEN-END:variables
}
