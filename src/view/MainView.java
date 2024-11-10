/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import enums.LaunchType;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FinancialControl;
import model.Launch;
import utils.ConverterUtils;

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
    
    /**
     * Retorna uma instância da class MainView
     *
     * @return MainView
     */
    public static MainView getMainView() {
        if (main == null) {
            main = new MainView();
        }
        
        return main;
    }
    
    /**
     * Método responsável para iniciar junto com a janela
     *
     */
    public void screen() {
        loadRelasesByDateTable();
        loadTotalBalance();
        loadCurrentBalance();
    }
    
    /**
     * Limpa as interações antiga que teve na janela
     *
     */
    public void resetInteractions() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        
        jDate.setText(currentDate);
    }
    
    /**
     * Carrega as colunas e os resultados na tabela
     *
     */
    private void loadRelasesByDateTable() {
        try {
            DefaultTableModel tableModel = new DefaultTableModel();
            
            tableModel.addColumn("Valor");
            tableModel.addColumn("Data");
            tableModel.addColumn("Tipo Lançamento");
            
            jReleasesByDateTable.setRowHeight(35);
                        
            List<Launch> listLauchByFilter = FinancialControl.listReleasesOrderByDate();
            int amountExpense = 0;
            int amountIncome = 0;
            
            for (Launch launch : listLauchByFilter) {
                Object[] row = {
                    ConverterUtils.formatToCurrency(launch.getAmount()),
                    ConverterUtils.formatToDate(launch.getDateTime()),
                    launch.getTypeToString(),
                };
                
                if (launch.getType().equals(LaunchType.EXPENSE)) {
                    amountExpense++;
                } else if (launch.getType().equals(LaunchType.INCOME)) {
                    amountIncome++;
                }
                
                tableModel.addRow(row);
            }
            
            jIncomeTitle.setText("Total de receitas: " + amountIncome);
            jExpenseTitle.setText("Total de despesas: " + amountExpense);
            
            jReleasesByDateTable.setModel(tableModel);
            jReleasesByDateTable.setVisible(false);
            jReleasesByDateTable.setVisible(true);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Carrega o saldo total e exibe na interface gráfica.
     * 
     */
    private void loadTotalBalance() {
        try {
            double totalBalance = FinancialControl.checkTotalBalance();
            jTotalBalance.setText("Saldo total é " + ConverterUtils.formatToCurrency(totalBalance));    
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar o saldo total", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Carrega o saldo atual e exibe na interface gráfica.
     * 
     */
    private void loadCurrentBalance() {
        try {
            double currentBalance = FinancialControl.checkCurrentBalance(LocalDateTime.now());
            jBalanceResult.setText("Seu saldo é " + ConverterUtils.formatToCurrency(currentBalance));    
        } catch (ArithmeticException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao formatar o saldo atual", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Mostra a janela de adicionar uma nova entrada
     *
     */
    private void showAddIncomeView() {
        AddIncomeView addIncomeView = AddIncomeView.getAddIncomeView();
        addIncomeView.screen();
        addIncomeView.setVisible(true);
        
        dispose();
    }
    
    /**
     * Mostra a janela de adicionar uma nova despasa
     *
     */
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
        jCheckBalance = new javax.swing.JPanel();
        jDateLabel = new javax.swing.JLabel();
        jDate = new javax.swing.JFormattedTextField();
        jBalanceResult = new javax.swing.JLabel();
        jTotalBalance = new javax.swing.JLabel();

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
                .addGap(25, 25, 25)
                .addComponent(jIncomeTitle)
                .addGap(10, 10, 10)
                .addComponent(jAddIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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
                .addGap(20, 20, 20)
                .addGroup(jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAddExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jExpenseTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jExpenseLayout.setVerticalGroup(
            jExpenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jExpenseLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jExpenseTitle)
                .addGap(10, 10, 10)
                .addComponent(jAddExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        jReleasesByDateTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jReleasesByDateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jReleasesByDateTable);

        jReleasesByDateTitle.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jReleasesByDateTitle.setText("Lançamentos (ordenados por data)");

        jCheckBalance.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar Saldo"));

        jDateLabel.setText("Data");

        try {
            jDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jBalanceResult.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBalanceResult.setText("Seu saldo é R$ 0000,00");

        javax.swing.GroupLayout jCheckBalanceLayout = new javax.swing.GroupLayout(jCheckBalance);
        jCheckBalance.setLayout(jCheckBalanceLayout);
        jCheckBalanceLayout.setHorizontalGroup(
            jCheckBalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jCheckBalanceLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jCheckBalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateLabel)
                    .addComponent(jBalanceResult, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jCheckBalanceLayout.setVerticalGroup(
            jCheckBalanceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jCheckBalanceLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDate, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jBalanceResult)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTotalBalance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTotalBalance.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jTotalBalance.setText("Saldo total é R$ 0000,00");

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(jIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jExpense, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(jCheckBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jMainLayout.createSequentialGroup()
                        .addComponent(jReleasesByDateTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTotalBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(27, 27, 27))
        );
        jMainLayout.setVerticalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jExpense, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jIncome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jCheckBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(44, 44, 44)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jReleasesByDateTitle)
                    .addComponent(jTotalBalance))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
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
    private javax.swing.JLabel jBalanceResult;
    private javax.swing.JPanel jCheckBalance;
    private javax.swing.JFormattedTextField jDate;
    private javax.swing.JLabel jDateLabel;
    private javax.swing.JPanel jExpense;
    private javax.swing.JLabel jExpenseTitle;
    private javax.swing.JPanel jIncome;
    private javax.swing.JLabel jIncomeTitle;
    private javax.swing.JPanel jMain;
    private javax.swing.JTable jReleasesByDateTable;
    private javax.swing.JLabel jReleasesByDateTitle;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jTotalBalance;
    // End of variables declaration//GEN-END:variables
}
