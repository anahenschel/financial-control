/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public class Expense extends Launch {
    private ExpenseCategory expenseCategory;
    
    public Expense() {
        this(null, 0, null);
    }
	
    public Expense(LocalDateTime dateTime, double amount, ExpenseCategory expenseCategory) {
	super(dateTime, amount);
        this.expenseCategory = expenseCategory;
    }
	
    public ExpenseCategory getExpenseCategory() {
	return expenseCategory;
    } 
	
    public void setExpenseCategory(ExpenseCategory expenseCategory) {
	if (expenseCategory == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
        
	this.expenseCategory = expenseCategory;
    }
}
