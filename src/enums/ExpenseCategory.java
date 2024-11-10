/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author nponcio
 */
public enum ExpenseCategory {
    DEFAULT("---"),
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
    
    /**
     * Converte uma descrição de categoria de despesa para o respectivo valor do enum ExpenseCategory.
     * 
     *
     * @param description A descrição da categoria de despesa que se deseja converter.
     * @return O valor correspondente do enum ExpenseCategory com a descrição fornecida.
     * @throws IllegalArgumentException Se a descrição fornecida não corresponder a nenhuma categoria válida
     *                                  de ExpenseCategory.
     */
    public static ExpenseCategory fromDescription(String description) {
        for (ExpenseCategory category : ExpenseCategory.values()) {
            if (category.toString().equalsIgnoreCase(description)) {
                return category;
            }
        }
        
        throw new IllegalArgumentException("Categoria de despesa inválida: " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}
