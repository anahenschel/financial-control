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
    private static PersistenceCSVImpl persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();
    
    /**
     * Cria as receitas com base nos parametros recebidos
     * 
     * @param 
     * @throws IOException
     */
    public static void createIncome(Income income) throws IOException {
        persistenceCSVImpl.saveRegister(income, null);
    }
    
    /**
     * Cria as despesas com base nos parametros recebidos
     * 
     * @param 
     * @throws IOException
     */
    public static void createExpense(Expense expense) throws IOException {
        persistenceCSVImpl.saveRegister(null, expense);
    }
    
    /**
     * Lista todas as receitas armazenadas
     */
    public static List<Income> listIncome() {
        List<Income> listIncome = new ArrayList<>();
        
        return listIncome;
    }
    
    /**
     * Lista todas as despesas armazenadas
     */
    public static List<Expense> listExpense() {
        List<Expense> listExpense = new ArrayList<>();
        
        return listExpense;
    }
}
