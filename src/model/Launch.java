/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.LaunchType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public abstract class Launch implements Comparable<Launch> {
    private LocalDateTime dateTime;
    private BigDecimal amount;
    private LaunchType type;
    
    /**
     * Construtor para a classe Launch que inicializa uma nova instância 
     * com a data, valor e tipo de lançamento especificados.
     *
     * @param dateTime A data e hora do lançamento, representada por um objeto LocalDateTime.
     * @param amount O valor do lançamento.
     * @param type O tipo do lançamento, representado por um objeto LaunchType.
     *
     * @throws IllegalArgumentException se type for LaunchType.ALL.
     */
    public Launch(LocalDateTime dateTime, BigDecimal amount, LaunchType type) {
        this.dateTime = dateTime;
        this.amount = amount;
        setType(type);
    }
	
    /**
     * Retorna a data e hora do lançamento.
     *
     * @return A data e hora do lançamento, representada por um objeto LocalDateTime.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    /**
     * Define a data e hora do lançamento.
     *
     * @param dateTime A data e hora do lançamento, representada por um objeto LocalDateTime.
     * @throws IllegalArgumentException se dateTime for null.
     */
    public void setDateTime(LocalDateTime dateTime) {
	if (dateTime == null) {
            throw new IllegalArgumentException("A data não pode ser nula");
        }
	
	this.dateTime = dateTime;
    }

    /**
     * Retorna o valor do lançamento.
     *
     * @return O valor do lançamento.
     */
    public BigDecimal getAmount() {
	return amount;
    }
	
    /**
     * Define o valor do lançamento.
     *
     * @param amount O valor do lançamento.
     * @throws IllegalArgumentException se amount for menor ou igual a zero.
     */
    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor não pode ser menor ou igual a zero");
        }
        
        this.amount = amount;
    }

    /**
     * Retorna o tipo do lançamento.
     *
     * @return O tipo do lançamento, representado por um objeto LaunchType.
     */
    public LaunchType getType() {
        return type;
    }

    /**
     * Define o tipo do lançamento.
     *
     * @param type O tipo do lançamento, representado por um objeto LaunchType.
     * @throws IllegalArgumentException se type for null ou igual a LaunchType.ALL.
     */
    public void setType(LaunchType type) {
        if (type == null || type == LaunchType.ALL) {
            throw new IllegalArgumentException("Um lançamento precisa ter um tipo específico");
        }
        
        this.type = type;
    }    
    
    /**
     * Compara este objeto Launch com outro objeto Launch 
     * com base no atributo dateTime.
     *
     * @param launch o objeto Launch a ser comparado.
     * @return um valor negativo, zero ou positivo se este objeto for 
     * respectivamente anterior, igual ou posterior ao objeto launch fornecido.
     */
    @Override
    public int compareTo(Launch launch) {
       return launch.dateTime.compareTo(this.dateTime);
    }
}
