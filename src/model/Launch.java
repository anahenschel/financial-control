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
public abstract class Launch {
    private LocalDateTime dateTime;
    private double amount;
    
    public Launch() {
        this(null, 0);
    }
    
    public Launch(LocalDateTime dateTime, double amount) {
        this.dateTime = dateTime;
        this.amount = amount;
    }
	
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
	if (dateTime == null) {
            throw new IllegalArgumentException("A data não pode ser nula");
        }
	
	this.dateTime = dateTime;
    }

    public double getAmount() {
	return amount;
    }
	
    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("O valor não pode ser menor do que zero");
        }
        
        this.amount = amount;
    }
}
