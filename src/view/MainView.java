/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lucas
 */
public class MainView extends javax.swing.JFrame {

    public static MainView main;
    
    /**
     * Creates new form Main
     */
    public MainView() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
    }
    
    public static MainView getMainView() {
        if (main == null) {
            main = new MainView();
        }
        
        return main;
    }
    
    public void screen() {
        loadRelasesByDateTable();
    }
    
    private void loadRelasesByDateTable() {
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Valor");
        tableModel.addColumn("Data");
        tableModel.addColumn("Tipo Lançamento");
        tableModel.addColumn("Categoria");
        
        jReleasesByDateTable.setModel(tableModel);
    }
    
    private void showAddIncomeView() {
        AddIncomeView addIncomeView = AddIncomeView.getAddIncomeView();
        addIncomeView.screen();
        addIncomeView.setVisible(true);
        
        dispose();
    }

    private void showAddExpenseView() {
        AddExpenseView addExpenseView = AddExpenseView.getAddExpenseView();
        addExpenseView.screen();
        addExpenseView.setVisible(true);
        
        dispose();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMain = new javax.swing.JPanel();
        jIncome = new javax.swing.JPanel();
        jAddIncome = new javax.swing.JToggleButton();
        jIncomeTitle = new javax.swing.JLabel();
        jExpense = new javax.swing.JPanel();
        jAddExpense = new javax.swing.JToggleButton();
        jExpenseTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jReleasesByDateTable = new javax.swing.JTable();
        jReleasesByDateTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jIncome.setBorder(javax.swing.BorderFactory.createTitledBorder("Receita"));

        jAddIncome.setText("Nova receita");
        jAddIncome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddIncome.setPreferredSize(new java.awt.Dimension(185, 40));
        jAddIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAddIncomeMouseClicked(evt);
            }
        });

        jIncomeTitle.setText("Total de receitas: 0");

        javax.swing.GroupLayout jIncomeLayout = new javax.swing.GroupLayout(jIncome);
        jIncome.setLayout(jIncomeLayout);
        jIncomeLayout.setHorizontalGroup(
            jIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIncomeLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIncomeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jIncomeLayout.setVerticalGroup(
            jIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIncomeLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jIncomeTitle)
                .addGap(10, 10, 10)
                .addComponent(jAddIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jExpense.setBorder(javax.swing.BorderFactory.createTitledBorder("Despesa"));

        jAddExpense.setText("Nova despesa");
        jAddExpense.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jAddExpense.setPreferredSize(new java.awt.Dimension(185, 40));
        jAddExpense.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAddExpenseMouseClicked(evt);
            }
        });

        jExpenseTitle.setText("Total de despesas: 0");

        javax.swing.GroupLayout jExpenseLayout = new javax.swing.GroupLayout(jExpense);
        jExpense.setLayout(jExpenseLayout);
        jExpenseLayout.setHorizontalGroup(
            jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExpenseLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jExpenseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jExpenseLayout.setVerticalGroup(
            jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExpenseLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jExpenseTitle)
                .addGap(10, 10, 10)
                .addComponent(jAddExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jReleasesByDateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jReleasesByDateTable);

        jReleasesByDateTitle.setText("Lançamentos (ordenados por data)");

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(jExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainLayout.createSequentialGroup()
                        .addComponent(jReleasesByDateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jMainLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(27, 27, 27))))
        );
        jMainLayout.setVerticalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jReleasesByDateTitle)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jAddIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAddIncomeMouseClicked
        showAddIncomeView();
    }//GEN-LAST:event_jAddIncomeMouseClicked

    private void jAddExpenseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAddExpenseMouseClicked
        showAddExpenseView();
    }//GEN-LAST:event_jAddExpenseMouseClicked

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton jAddExpense;
    private javax.swing.JToggleButton jAddIncome;
    private javax.swing.JPanel jExpense;
    private javax.swing.JLabel jExpenseTitle;
    private javax.swing.JPanel jIncome;
    private javax.swing.JLabel jIncomeTitle;
    private javax.swing.JPanel jMain;
    private javax.swing.JTable jReleasesByDateTable;
    private javax.swing.JLabel jReleasesByDateTitle;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
