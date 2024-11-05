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
public class Income extends Launch {
    private IncomeCategory incomeCategory;
	
    public Income() {
        this(null, 0, null);
    }
	
    public Income(LocalDateTime dateTime, double amount, IncomeCategory incomeCategory) {
	super(dateTime, amount);
	this.incomeCategory = incomeCategory;
    }
	
    public IncomeCategory getIncomeCategory() {
	return incomeCategory;
    } 

    public void setIncomeCategory(IncomeCategory incomeCategory) {
	if (incomeCategory == null) {
            throw new IllegalArgumentException("Categoria não pode ser nula");
        }
	
        this.incomeCategory = incomeCategory;
    }
}
