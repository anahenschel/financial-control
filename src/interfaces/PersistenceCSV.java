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
    public void createFile() throws IOException;
    public void saveRegister(Income income, Expense expense) throws IOException;
    public List<Object> listRegisterByType(LaunchType type);
}
