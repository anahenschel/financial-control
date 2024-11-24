/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author lhenschel
 */
public enum LaunchType {
    ALL("Todos"),
    INCOME("Receita"),
    EXPENSE("Despesa");
    
    private final String description;

    LaunchType(String description) {
        this.description = description;
    }
    
    public static LaunchType fromDescription(String description) {
        for (LaunchType category : LaunchType.values()) {
            if (category.toString().equalsIgnoreCase(description)) {
                return category;
            }
        }
        
        throw new IllegalArgumentException("Tipo de lançamento inválida: " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}
