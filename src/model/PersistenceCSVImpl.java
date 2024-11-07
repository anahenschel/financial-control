/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.LaunchType;
import interfaces.PersistenceCSV;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhenschel
 */
public class PersistenceCSVImpl implements PersistenceCSV{

    public static PersistenceCSVImpl persistenceCSV;
    
    public static PersistenceCSVImpl getPersistenceCSVImpl() {
        if(persistenceCSV == null) {
            persistenceCSV = new PersistenceCSVImpl();
        }
        
        return persistenceCSV;
    }
    
    @Override
    public void createFile() {
        File launchFile = new File("launch.csv");
        
        if (!launchFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(launchFile), "UTF-8"))) {
                String[] headers = {"launchType", "category", "dateTime", "amount"};
                
                writer.write(String.join(";", headers));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveRegister(Income income, Expense expense) {
        
    }

    @Override
    public List<Object> listRegisterByType(LaunchType type) {
        List<Object> listRegister = new ArrayList<>();
        
        return listRegister;
    }
}
