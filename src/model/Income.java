/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.IncomeCategory;
import enums.LaunchType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public class Income extends Launch {
    private IncomeCategory incomeCategory;
	
    /**
     * Construtor padrão para a classe Income.
     * Inicializa uma nova instância do Income com valores padrão.
     *
     */
    public Income() {
        super(null, BigDecimal.ZERO, LaunchType.INCOME, BigDecimal.ZERO);
    }
	
    /**
     * Construtor para a classe Income que inicializa uma nova instância 
     * com a data, valor e categoria de receita especificados.
     *
     * @param dateTime A data e hora da receita, representada por um objeto LocalDateTime.
     * @param amount O valor da receita.
     * @param incomeCategory A categoria da receita, representada por um objeto IncomeCategory.
     * @param totalBalance Saldo total quando foi realizada a transação
     *
     */
    public Income(LocalDateTime dateTime, BigDecimal amount, IncomeCategory incomeCategory, BigDecimal totalBalance) {
	super(dateTime, amount, LaunchType.INCOME, totalBalance);
	this.incomeCategory = incomeCategory;
    }
	
    /**
     * Retorna a categoria da receita associada a esta instância do Income.
     *
     * @return A categoria da receita, representada por um objeto IncomeCategory.
     */
    public IncomeCategory getIncomeCategory() {
	return incomeCategory;
    } 

    /**
     * Define a categoria da receita para esta instância do Income.
     *
     * @param incomeCategory A categoria da receita, representada por um objeto IncomeCategory.
     * @throws IllegalArgumentException se incomeCategory for null ou igual a IncomeCategory.DEFAULT.
     */
    public void setIncomeCategory(IncomeCategory incomeCategory) {
	if (incomeCategory == null || incomeCategory == IncomeCategory.DEFAULT) {
            throw new IllegalArgumentException("Categoria deve ser informada");
        }
	
        this.incomeCategory = incomeCategory;
    }
}
