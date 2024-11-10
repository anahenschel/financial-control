/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author nponcio
 */
public enum IncomeCategory {
    DEFAULT("---"),
    SALARY("Salário"),
    THIRTEENTH_SALARY("Décimo Terceiro Salário"),
    VACATION("Férias"),
    BONUSES_AND_COMMISSIONS("Bônus e Comissões"),
    INVESTMENTS("Investimentos"),
    REAL_ESTATE_INCOME("Rendimento de Imóveis"),
    AID("Auxílios"),
    OTHER_INCOME("Outras Receitas");

    private final String description;

    IncomeCategory(String description) {
        this.description = description;
    }
    
    /**
     * Converte uma descrição de categoria de receita para o respectivo valor do enum IncomeCategory.
     * 
     *
     * @param description A descrição da categoria de receita que se deseja converter.
     * @return O valor correspondente do enum IncomeCategory com a descrição fornecida.
     * @throws IllegalArgumentException Se a descrição fornecida não corresponder a nenhuma categoria válida
     *                                  de IncomeCategory.
     */
    public static IncomeCategory fromDescription(String description) {
        for (IncomeCategory category : IncomeCategory.values()) {
            if (category.toString().equalsIgnoreCase(description)) {
                return category;
            }
        }
        
        throw new IllegalArgumentException("Categoria de receita inválida: " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}
