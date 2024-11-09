/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.LaunchType;
import interfaces.PersistenceCSV;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhenschel
 */
public class PersistenceCSVImpl implements PersistenceCSV {
    public static PersistenceCSVImpl persistenceCSV;
    
    private static final String ADD_COLUMN = ";";
    private File launchFile;
    
    public static PersistenceCSVImpl getPersistenceCSVImpl() {
        if (persistenceCSV == null) {
            persistenceCSV = new PersistenceCSVImpl();
        }
        
        return persistenceCSV;
    }
    
    @Override
    public void createFile() throws IOException {
        launchFile = new File("launch.csv");
        
        if (!launchFile.exists()) {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(launchFile), "UTF-8"));
                String[] headers = {"launchType", "category", "dateTime", "amount"};

                writer.write(String.join(ADD_COLUMN, headers));
                writer.newLine();    
            } catch (IOException ex) {
                throw new IOException("Erro ao criar o arquivo");
            }
        }
    }

    @Override
    public void saveRegister(Income income, Expense expense) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(launchFile, true))) {
            StringBuilder data = new StringBuilder();
            
            if (income != null) {
                data.append(income.getType()).append(ADD_COLUMN);
                data.append(income.getIncomeCategory()).append(ADD_COLUMN);
                data.append(income.getDateTime().toString()).append(ADD_COLUMN);
                data.append(income.getAmount());
            } else if (expense != null) {
                data.append(expense.getType()).append(ADD_COLUMN);
                data.append(expense.getExpenseCategory()).append(ADD_COLUMN);
                data.append(expense.getDateTime().toString()).append(ADD_COLUMN);
                data.append(expense.getAmount());
            }
            
            writer.println(data.toString());
        } catch (IOException e) {
            throw new IOException("Erro ao gravar o registro no arquivo");
        }
    }

    @Override
    public List<Object> listRegisterByType(LaunchType type) throws IOException {
        List<Object> listRegister = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(launchFile))) {
            String line;
            boolean isFirstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                
                String[] columns = line.split(ADD_COLUMN);
                
                if (type == LaunchType.INCOME && columns[0].equals(LaunchType.INCOME)) {
                    listRegister.add(columns);
                } else if (type == LaunchType.EXPENSE && columns[0].equals(LaunchType.EXPENSE)) {
                    listRegister.add(columns);
                } else if (type == LaunchType.ALL) {
                    listRegister.add(columns);
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar os registro do arquivo");
        }
        
        return listRegister;
    }
}
