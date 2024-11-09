/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class FinancialControl {
    private static final PersistenceCSVImpl persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();
    
    /**
     * Cria as receitas com base nos parametros recebidos
     * 
     * @param amount
     * @param incomeCategory
     * @param dateTime
     * @throws IOException
     */
    public static void createIncome(double amount, IncomeCategory incomeCategory, LocalDateTime dateTime) throws IOException, IllegalArgumentException {
        Income income = new Income();
        income.setDateTime(dateTime);
        income.setAmount(amount);
        income.setIncomeCategory(incomeCategory);
        
        persistenceCSVImpl.saveRegister(income, null);
    }
    
    /**
     * Cria as despesas com base nos parametros recebidos
     * 
     * @param amount
     * @param expenseCategory
     * @param dateTime 
     * @throws IOException
     */
    public static void createExpense(double amount, ExpenseCategory expenseCategory, LocalDateTime dateTime) throws IOException, IllegalArgumentException { 
        Expense expense = new Expense();
        expense.setDateTime(dateTime);
        expense.setAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        
        persistenceCSVImpl.saveRegister(null, expense);
    }
    
    /**
     * Lista todas as receitas armazenadas.
     *
     * @return Uma lista de objetos Income representando todas as receitas.
     */
    public static List<Income> listIncome() {
        List<Income> listIncome = new ArrayList<>();
        
        return listIncome;
    }
    
    /**
     * Lista todas as despesas armazenadas.
     *
     * @return Uma lista de objetos Expense representando todas as despesas.
     */
    public static List<Expense> listExpense() {
        List<Expense> listExpense = new ArrayList<>();
        
        return listExpense;
    }
    
    /**
     * Lista todos os lançamentos filtrados por uma data e hora específicas.
     *
     * @param dataTime A data e hora usada como filtro para os lançamentos.
     * @return Uma lista de objetos Launch que correspondem ao filtro de data e hora especificado.
     */
    public static List<Launch> listReleasesByFilter(LocalDateTime dataTime) {
        List<Launch> listLaunchByFilter = new ArrayList<>();
        
        return listLaunchByFilter;
    }
    
    /**
     * Verifica o saldo atual em uma data e hora específica.
     *
     * @param dateTime A data e hora para a qual o saldo atual será verificado.
     * @return O saldo atual como um valor double para a data e hora especificada.
     */
    public static double checkCurrentBalance(LocalDateTime dateTime) {
       double currentBalance = 0;
       
       return currentBalance;
    }
    
    /**
     * Verifica o saldo total acumulado.
     *
     * @return O saldo total acumulado como um valor double.
     */
    public static double checkTotalBalance() {
       double totalBalance = 0;
       
       return totalBalance;
    }
}
