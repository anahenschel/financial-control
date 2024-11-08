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

    @Override
    public String toString() {
        return description;
    }
}
