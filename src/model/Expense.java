/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.LaunchType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public class Expense extends Launch {
    private ExpenseCategory expenseCategory;
    
    /**
     * Construtor padrão para a classe Expense.
     * Inicializa uma nova instância do Expense com valores padrão.
     *
     */
    public Expense() {
        super(null, BigDecimal.ZERO, LaunchType.EXPENSE, BigDecimal.ZERO);
    }
	
    /**
     * Construtor para a classe Expense que inicializa uma nova instância com a data, 
     * valor e categoria de despesa especificados.
     *
     * @param dateTime A data e hora da despesa, representada pelo objeto LocalDateTime.
     * @param amount O valor da despesa.
     * @param expenseCategory A categoria da despesa, representada pelo objeto ExpenseCategory.
     *
     */
    public Expense(LocalDateTime dateTime, BigDecimal amount, ExpenseCategory expenseCategory, BigDecimal totalBalance) {
	super(dateTime, amount, LaunchType.EXPENSE, totalBalance);
        this.expenseCategory = expenseCategory;
    }
	
    /**
     * Retorna a categoria da despesa associada a esta instância do Expense.
     *
     * @return A categoria da despesa, representada por um objeto ExpenseCategory.
     */
    public ExpenseCategory getExpenseCategory() {
	return expenseCategory;
    } 
	
    /**
    * Define a categoria da despesa para esta instância do Expense.
    *
    *
    * @param expenseCategory A categoria da despesa, representada por um objeto ExpenseCategory.
    * @throws IllegalArgumentException se expenseCategory for null ou igual a ExpenseCategory.DEFAULT.
    */
    public void setExpenseCategory(ExpenseCategory expenseCategory) {
	if (expenseCategory == null || expenseCategory == ExpenseCategory.DEFAULT) {
            throw new IllegalArgumentException("Categoria deve ser informada");
        }
        
	this.expenseCategory = expenseCategory;
    }
}
