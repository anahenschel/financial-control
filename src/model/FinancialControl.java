/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FinancialControl {
    public static FinancialControl financialControl;
    
    private PersistenceCSVImpl persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();
    
    public static FinancialControl getFinancialControl() {
        if (financialControl == null) {
            financialControl = new FinancialControl();
        }
        
        return financialControl;
    }
    
    public void createIncome(Income income) throws IOException {
        persistenceCSVImpl.saveRegister(income, null);
    }
    
    public void createExpense(Expense expense) throws IOException {
        persistenceCSVImpl.saveRegister(null, expense);
    }
    
    public List<Income> listIncome() {
        List<Income> listIncome = new ArrayList<>();
        
        return listIncome;
    }
    
    public List<Expense> listExpense() {
        List<Expense> listExpense = new ArrayList<>();
        
        return listExpense;
    }
}
