/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model;

/**
 *
 * @author nponcio
 */
public enum ExpenseCategory {
    FOOD("Comida"),
    TRANSPORT("Transporte"),
    RESIDENCE("Residência"),
    HEALTH("Saúde"),
    EDUCATION("Educação"),
    ENTERTAINMENT("Entretenimento"),
    CLOTHING("Roupas"),
    COMMUNICATION("Comunicação"),
    INSURANCE("Seguro"),
    SERVICES("Serviços"),
    DEBT_LOANS("Empréstimos e Dívidas"),
    OTHER_EXPENSES("Outras Despesas");

    private final String description;

    ExpenseCategory(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
