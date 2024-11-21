/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import enums.LaunchType;
import java.io.IOException;
import java.util.List;
import model.Expense;
import model.Income;

/**
 *
 * @author lhenschel
 */
public interface PersistenceCSV {
    
    /**
     * Cria um arquivo para armazenar os registros financeiros.
     * Lança uma exceção caso ocorra um erro durante a criação do arquivo.
     * 
     * @param file Nome do arquivo
     * @throws IOException
     */
    public void createFile(String file) throws IOException;
    
    /**
     * Salva um novo registro de receitas ou despesas no arquivo.
     * Lança uma exceção caso ocorra um erro durante a inserção do registro.
     * 
     * @param income
     * @param expense
     * @throws IOException
     */
    public void saveRegister(Income income, Expense expense) throws IOException;
    
    /**
     * Lista todos os registros com base no tipo especificado
     * Lança uma exceção caso ocorra um erro durante a listagem.
     * 
     * @param type
     * @return {@code List<Object>}
     * @throws IOException
     */
    public List<Object> listRegisterByType(LaunchType type) throws IOException;
}
