/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import enums.LaunchType;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersistenceCSVImplTest {
    
    PersistenceCSVImpl persistence;

    @Before
    public void setUp() throws IOException {
        persistence = PersistenceCSVImpl.getPersistenceCSVImpl();
        persistence.createFile("./test/files/persistenceCSVImplTest.csv");
    }

    @After
    public void tearDown() {
        persistence.getLaunchFile().delete();
    }

    @Test
    public void testCaso1_CriarArquivoComCabecalho() throws IOException {
        persistence.createFile("./test/files/createFileTest.csv");
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
       
        assertEquals("launchType;category;dateTime;amount", linhas.get(0));
    }

    @Test
    public void testCaso2_ArquivoJaExisteNaoCriaNovamente() throws IOException {

        //ignorar por enquanto 
        persistence.createFile("./test/files/persistenceCSVImplTest.csv");

    }

    @Test
    public void testCaso3_SalvarReceita() throws IOException {
        Income income = new Income(LocalDateTime.parse("2024-11-12T10:00:00"), 5000.0, IncomeCategory.SALARY, 0);

        persistence.saveRegister(income, null);
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
        assertEquals("INCOME;Salário;2024-11-12T10:00;5000.0", linhas.get(1));
    };

    @Test
    public void testCaso4_SalvarDespesa() throws IOException {
        Expense expense = new Expense(LocalDateTime.parse("2024-11-12T10:00:00"), 1000.0, ExpenseCategory.SERVICES, 0);
 
        persistence.saveRegister(null, expense);

        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
        assertEquals("EXPENSE;Serviços;2024-11-12T10:00;1000.0", linhas.get(1));

    }

    @Test
    public void testCaso5_ListarTodosRegistros() throws IOException {
        Income income = new Income(LocalDateTime.parse("2024-11-12T10:00:00"), 5000.0, IncomeCategory.SALARY, 0);
        Expense expense = new Expense(LocalDateTime.parse("2024-11-12T10:00:00"), 1000.0, ExpenseCategory.SERVICES, 0);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);

        //validar se o primeiro registro é income e validar se o segundo é expense
        List<Object> registros = persistence.listRegisterByType(LaunchType.ALL);
        assertEquals(2, registros.size());
    }

    @Test
    public void testCaso6_ListarSomenteReceitas() throws IOException {
        Income income = new Income(LocalDateTime.parse("2024-11-12T10:00:00"), 5000.0, IncomeCategory.SALARY, 0);
        Expense expense = new Expense(LocalDateTime.parse("2024-11-12T10:00:00"), 1000.0, ExpenseCategory.SERVICES, 0);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);

        List<Object> registros = persistence.listRegisterByType(LaunchType.INCOME);
        //validar se o primeiro registro é income
        assertEquals(1, registros.size());
    }

    @Test
    public void testCaso7_ListarSomenteDespesas() throws IOException {
        Income income = new Income(LocalDateTime.parse("2024-11-12T10:00:00"), 5000.0, IncomeCategory.SALARY, 0);
        Expense expense = new Expense(LocalDateTime.parse("2024-11-12T10:00:00"), 1000.0, ExpenseCategory.SERVICES, 0);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);

        //validar se o primeiro registro é expense
        List<Object> registros = persistence.listRegisterByType(LaunchType.EXPENSE);
        assertEquals(1, registros.size());
    }
}
