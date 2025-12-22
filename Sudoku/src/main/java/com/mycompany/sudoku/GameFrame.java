package com.mycompany.sudoku;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameFrame extends javax.swing.JFrame {

    SudokuController controller = new SudokuController();
    SudokuViewAdapter viewer = new SudokuViewAdapter(controller);
    private int[][] board;

    
    public GameFrame(int[][] board) {
        this.board = board;
        initComponents();
        attachAutoSaveAll();
        updateBoardUI();
    }
    private void logUserActionForCell(int x, int y, int oldValue, int newValue) {
        UserAction action = new UserAction(x, y, oldValue, newValue);
        try {
            controller.logUserAction(action.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void attachAutoSave(JTextField textField, int x, int y) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            private String previousValue = textField.getText();

            @Override
            public void insertUpdate(DocumentEvent e) {
                saveChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                saveChange();
            }

            private void saveChange() {
                try {
                    String newText = textField.getText();
                    int newValue = newText.isEmpty() ? 0 : Integer.parseInt(newText);
                    int oldValue = previousValue.isEmpty() ? 0 : Integer.parseInt(previousValue);

                    board[x][y] = newValue;

                   
                    logUserActionForCell(x, y, oldValue, newValue);

                    previousValue = newText;
                } catch (NumberFormatException ex) {
                    textField.setText(previousValue);
                }
            }
        });
    }

    private void attachAutoSaveAll() {
        for (int i = 1; i <= 81; i++) {
            try {
                Field f = GameFrame.class.getDeclaredField("jTextField" + i);
                f.setAccessible(true);
                JTextField tf = (JTextField) f.get(this);
                int row = (i - 1) / 9;
                int col = (i - 1) % 9;
                attachAutoSave(tf, row, col);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBoardUI() {
        for (int i = 1; i <= 81; i++) {
            try {
                Field f = GameFrame.class.getDeclaredField("jTextField" + i);
                f.setAccessible(true);
                JTextField tf = (JTextField) f.get(this);
                int row = (i - 1) / 9;
                int col = (i - 1) % 9;
                tf.setText(board[row][col] == 0 ? "" : String.valueOf(board[row][col]));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        jTextField44 = new javax.swing.JTextField();
        jTextField45 = new javax.swing.JTextField();
        jTextField46 = new javax.swing.JTextField();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        jTextField53 = new javax.swing.JTextField();
        jTextField54 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField74 = new javax.swing.JTextField();
        jTextField75 = new javax.swing.JTextField();
        jTextField76 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jTextField81 = new javax.swing.JTextField();
        VerifyButton = new javax.swing.JButton();
        SolveButton = new javax.swing.JButton();
        UndoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(9, 9));
        jPanel1.add(jTextField1);
        jPanel1.add(jTextField2);
        jPanel1.add(jTextField3);
        jPanel1.add(jTextField4);
        jPanel1.add(jTextField5);
        jPanel1.add(jTextField6);
        jPanel1.add(jTextField7);
        jPanel1.add(jTextField8);
        jPanel1.add(jTextField9);
        jPanel1.add(jTextField10);
        jPanel1.add(jTextField11);
        jPanel1.add(jTextField12);
        jPanel1.add(jTextField13);
        jPanel1.add(jTextField14);
        jPanel1.add(jTextField15);
        jPanel1.add(jTextField16);
        jPanel1.add(jTextField17);
        jPanel1.add(jTextField18);
        jPanel1.add(jTextField19);
        jPanel1.add(jTextField20);
        jPanel1.add(jTextField21);
        jPanel1.add(jTextField22);
        jPanel1.add(jTextField23);
        jPanel1.add(jTextField24);
        jPanel1.add(jTextField25);
        jPanel1.add(jTextField26);
        jPanel1.add(jTextField27);
        jPanel1.add(jTextField28);
        jPanel1.add(jTextField29);
        jPanel1.add(jTextField30);
        jPanel1.add(jTextField31);
        jPanel1.add(jTextField32);
        jPanel1.add(jTextField33);
        jPanel1.add(jTextField34);
        jPanel1.add(jTextField35);
        jPanel1.add(jTextField36);
        jPanel1.add(jTextField37);
        jPanel1.add(jTextField38);
        jPanel1.add(jTextField39);
        jPanel1.add(jTextField40);
        jPanel1.add(jTextField41);
        jPanel1.add(jTextField42);
        jPanel1.add(jTextField43);
        jPanel1.add(jTextField44);
        jPanel1.add(jTextField45);
        jPanel1.add(jTextField46);
        jPanel1.add(jTextField47);
        jPanel1.add(jTextField48);
        jPanel1.add(jTextField49);
        jPanel1.add(jTextField50);
        jPanel1.add(jTextField51);
        jPanel1.add(jTextField52);
        jPanel1.add(jTextField53);
        jPanel1.add(jTextField54);
        jPanel1.add(jTextField55);
        jPanel1.add(jTextField56);
        jPanel1.add(jTextField57);
        jPanel1.add(jTextField58);
        jPanel1.add(jTextField59);
        jPanel1.add(jTextField60);
        jPanel1.add(jTextField61);
        jPanel1.add(jTextField62);
        jPanel1.add(jTextField63);
        jPanel1.add(jTextField64);
        jPanel1.add(jTextField65);
        jPanel1.add(jTextField66);
        jPanel1.add(jTextField67);
        jPanel1.add(jTextField68);
        jPanel1.add(jTextField69);
        jPanel1.add(jTextField70);
        jPanel1.add(jTextField71);
        jPanel1.add(jTextField72);
        jPanel1.add(jTextField73);
        jPanel1.add(jTextField74);
        jPanel1.add(jTextField75);
        jPanel1.add(jTextField76);
        jPanel1.add(jTextField77);
        jPanel1.add(jTextField78);
        jPanel1.add(jTextField79);
        jPanel1.add(jTextField80);

        jTextField81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField81ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField81);

        VerifyButton.setText("Verify");
        VerifyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VerifyButtonActionPerformed(evt);
            }
        });

        SolveButton.setText("Solve");
        SolveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SolveButtonActionPerformed(evt);
            }
        });

        UndoButton.setText("Undo");
        UndoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UndoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(VerifyButton)
                .addGap(152, 152, 152)
                .addComponent(SolveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UndoButton)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(VerifyButton)
                    .addComponent(SolveButton)
                    .addComponent(UndoButton))
                .addGap(0, 36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField81ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField81ActionPerformed

    private void VerifyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VerifyButtonActionPerformed
        viewer.verifyGame(board);
    }//GEN-LAST:event_VerifyButtonActionPerformed

    private void SolveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SolveButtonActionPerformed
        try {
            viewer.solveGame(board);
        } catch (InvalidGameException ex) {
            Logger.getLogger(GameFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SolveButtonActionPerformed

    private void UndoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UndoButtonActionPerformed
        viewer.undoGame(board);
     }//GEN-LAST:event_UndoButtonActionPerformed

    public static void main(String args[]) {

      int[][] initialBoard = new int[9][9];
        java.awt.EventQueue.invokeLater(() -> new GameFrame(initialBoard).setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton SolveButton;
    private javax.swing.JButton UndoButton;
    private javax.swing.JButton VerifyButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField45;
    private javax.swing.JTextField jTextField46;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField53;
    private javax.swing.JTextField jTextField54;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField74;
    private javax.swing.JTextField jTextField75;
    private javax.swing.JTextField jTextField76;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
