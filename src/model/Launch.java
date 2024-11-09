/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.LaunchType;
import java.time.LocalDateTime;

/**
 *
 * @author nponcio
 */
public abstract class Launch {
    private LocalDateTime dateTime;
    private double amount;
    private LaunchType type;
    
    public Launch(LocalDateTime dateTime, double amount, LaunchType type) {
        this.dateTime = dateTime;
        this.amount = amount;
        this.type = type;
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
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor não pode ser menor ou igual a zero");
        }
        
        this.amount = amount;
    }

    public LaunchType getType() {
        return type;
    }

    public void setType(LaunchType type) {
        if (type == null || type == LaunchType.ALL) {
            throw new IllegalArgumentException("Um lançamento precisa ter um tipo específico");
        }
        
        this.type = type;
    }    
}
