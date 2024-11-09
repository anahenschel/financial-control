/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import enums.IncomeCategory;
import enums.LaunchType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.FinancialControl;
import model.Income;

/**
 *
 * @author lucas
 */
public class AddIncomeView extends javax.swing.JFrame {

    public static AddIncomeView addIncomeView;
    
    /**
     * Creates new form AddIncomeView
     */
    public AddIncomeView() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        
        listIncomeCategory();
    }
    
    /**
     * Retorna uma instância da class AddIncomeView
     *
     * @return AddIncomeView
     */
    public static AddIncomeView getAddIncomeView() {
        if (addIncomeView == null) {
            addIncomeView = new AddIncomeView();
        }
        
        return addIncomeView;
    }
    
    /**
     * Método de inicialização da janela
     *
     */
    public void screen() {
        listIncome();
    }
    
    private void listIncome() {
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("Valor");
        tableModel.addColumn("Data");
        tableModel.addColumn("Categoria");
        
        List<Income> listIncome = FinancialControl.listIncome();
        
        for (Income income : listIncome) {
            Object[] row = {
                income.getAmount(),
                income.getDateTime(),
                income.getIncomeCategory()
            };
            
            tableModel.addRow(row);
        }
        
        jIncomeTable.setModel(tableModel);
        jIncomeTable.setVisible(false);
        jIncomeTable.setVisible(true);
    }
    
    private void listIncomeCategory() {
        for (IncomeCategory incomeCategory : IncomeCategory.values()) {
            jIncomeCategory.addItem(incomeCategory);
        }
    }
    
    /**
     * Mostrar a tela principal MainView
     *
     */
    private void showMainView() {
        MainView mainView = MainView.getMainView();
        mainView.screen();
        mainView.setVisible(true);
        
        dispose();
    }
    
    private boolean validInformations() {
        return !jDateTime.getText().equals("") && !jAmount.getText().equals("") && !jIncomeCategory.getSelectedItem().equals(IncomeCategory.DEFAULT.toString());
    }
    
    private void saveIncome() {
        try {
            if (validInformations()) {
                Income income = new Income();

                income.setAmount(Double.parseDouble(jAmount.getText().replace(",", ".")));
                income.setIncomeCategory((IncomeCategory) jIncomeCategory.getSelectedItem());
                income.setDateTime(LocalDateTime.parse(jDateTime.getText()));

                FinancialControl.createIncome(income);
                resetInteractions();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void resetInteractions() {
        jDateTime.setText("");
        jIncomeCategory.setSelectedIndex(0);
        jAmount.setText("");
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
        jIncomeTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jIncomeTable = new javax.swing.JTable();
        jIncome = new javax.swing.JPanel();
        jIncomeForm = new javax.swing.JPanel();
        jDateTime = new javax.swing.JFormattedTextField();
        jDateTimeLabel = new javax.swing.JLabel();
        jAmountLabel = new javax.swing.JLabel();
        jAmount = new javax.swing.JTextField();
        jIncomeCategoryLabel = new javax.swing.JLabel();
        jIncomeCategory = new javax.swing.JComboBox<>();
        jSaveIncome = new javax.swing.JToggleButton();
        jBackWindow = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jIncomeTitle.setText("Receitas");

        jIncomeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jIncomeTable);

        jIncomeForm.setBorder(javax.swing.BorderFactory.createTitledBorder("Adicionar Receita"));

        try {
            jDateTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jDateTime.setPreferredSize(new java.awt.Dimension(300, 40));

        jDateTimeLabel.setText("Data do lançamento");

        jAmountLabel.setText("Valor");

        jAmount.setPreferredSize(new java.awt.Dimension(300, 40));

        jIncomeCategoryLabel.setText("Tipo de receita");

        jIncomeCategory.setPreferredSize(new java.awt.Dimension(300, 40));

        jSaveIncome.setText("Salvar Receita");
        jSaveIncome.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSaveIncome.setPreferredSize(new java.awt.Dimension(200, 40));
        jSaveIncome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSaveIncomeMouseClicked(evt);
            }
        });

        jBackWindow.setText("Voltar");
        jBackWindow.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jBackWindow.setPreferredSize(new java.awt.Dimension(200, 40));
        jBackWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBackWindowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jIncomeFormLayout = new javax.swing.GroupLayout(jIncomeForm);
        jIncomeForm.setLayout(jIncomeFormLayout);
        jIncomeFormLayout.setHorizontalGroup(
            jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIncomeFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAmountLabel)
                    .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jIncomeFormLayout.createSequentialGroup()
                            .addComponent(jBackWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(50, 50, 50)
                            .addComponent(jSaveIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jIncomeFormLayout.createSequentialGroup()
                            .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateTimeLabel)
                                .addComponent(jDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jIncomeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jIncomeCategoryLabel)))))
                .addGap(20, 20, 20))
        );
        jIncomeFormLayout.setVerticalGroup(
            jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jIncomeFormLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jIncomeFormLayout.createSequentialGroup()
                        .addComponent(jDateTimeLabel)
                        .addGap(5, 5, 5)
                        .addComponent(jDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jIncomeFormLayout.createSequentialGroup()
                        .addComponent(jIncomeCategoryLabel)
                        .addGap(5, 5, 5)
                        .addComponent(jIncomeCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jAmountLabel)
                .addGap(5, 5, 5)
                .addComponent(jAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jIncomeFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSaveIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBackWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jIncomeLayout = new javax.swing.GroupLayout(jIncome);
        jIncome.setLayout(jIncomeLayout);
        jIncomeLayout.setHorizontalGroup(
            jIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jIncomeLayout.createSequentialGroup()
                .addContainerGap(150, Short.MAX_VALUE)
                .addComponent(jIncomeForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jIncomeLayout.setVerticalGroup(
            jIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jIncomeLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jIncomeForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jMainLayout = new javax.swing.GroupLayout(jMain);
        jMain.setLayout(jMainLayout);
        jMainLayout.setHorizontalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jMainLayout.createSequentialGroup()
                        .addComponent(jIncomeTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addComponent(jIncome, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jMainLayout.setVerticalGroup(
            jMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jMainLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jIncome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jIncomeTitle)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBackWindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBackWindowMouseClicked
        showMainView();
    }//GEN-LAST:event_jBackWindowMouseClicked

    private void jSaveIncomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSaveIncomeMouseClicked
        saveIncome();
    }//GEN-LAST:event_jSaveIncomeMouseClicked

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
            java.util.logging.Logger.getLogger(AddIncomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddIncomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddIncomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddIncomeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddIncomeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jAmount;
    private javax.swing.JLabel jAmountLabel;
    private javax.swing.JToggleButton jBackWindow;
    private javax.swing.JFormattedTextField jDateTime;
    private javax.swing.JLabel jDateTimeLabel;
    private javax.swing.JPanel jIncome;
    private javax.swing.JComboBox<IncomeCategory> jIncomeCategory;
    private javax.swing.JLabel jIncomeCategoryLabel;
    private javax.swing.JPanel jIncomeForm;
    private javax.swing.JTable jIncomeTable;
    private javax.swing.JLabel jIncomeTitle;
    private javax.swing.JPanel jMain;
    private javax.swing.JToggleButton jSaveIncome;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
