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
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class PersistenceCSVImplTest {
    
    private PersistenceCSVImpl persistence;

    @Before
    public void setUp() throws IOException {
        persistence = PersistenceCSVImpl.getPersistenceCSVImpl();
        persistence.createFile("./test/model/persistenceCSVImplTest.csv");
    }

    @After
    public void tearDown() {
        persistence.getLaunchFile().delete();
    }

    @Test
    public void testCaso1_CriarArquivoComCabecalho() throws IOException {
        persistence.createFile("./test/model/createFileTest.csv");
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
       
        assertEquals("\uFEFFlaunchType;category;dateTime;amount", linhas.get(0));
    }
    
    @Test(expected = IOException.class)
    public void testCaso2_NaoAceitaCaminhoInvalido() throws IOException {
        persistence.createFile("./InvalidDirectory/file.csv");
    }

    @Test
    public void testCaso3_SalvarReceita() throws IOException {
        Income income = new Income(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(5000.0), IncomeCategory.SALARY);

        persistence.saveRegister(income, null);
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
        assertEquals("Receita;Salário;2024-01-01T00:00;5000", linhas.get(1));
    };

    @Test
    public void testCaso4_SalvarDespesa() throws IOException {
        Expense expense = new Expense(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(1000.0), ExpenseCategory.SERVICES);
 
        persistence.saveRegister(null, expense);

        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
        assertEquals("Despesa;Serviços;2024-01-01T00:00;1000", linhas.get(1));
    }

    @Test
    public void testCaso5_ListarTodosRegistros() throws IOException {
        Income income = new Income(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(5000.0), IncomeCategory.SALARY);
        Expense expense = new Expense(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0), new BigDecimal(1000.0), ExpenseCategory.SERVICES);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);
        
        List<Object> listAll = persistence.listRegisterByType(LaunchType.ALL);
        List<LaunchType> typeRegisters = new ArrayList<LaunchType>();

        for (Object register : listAll) {
            if (register instanceof String[] columns) {
                 LaunchType launchType = LaunchType.fromDescription(columns[0]);
                 typeRegisters.add(launchType);
            }
        }

        assertEquals(2, typeRegisters.size());
        assertEquals(income.getType(), typeRegisters.get(0));
        assertEquals(expense.getType(), typeRegisters.get(1));
    }

    @Test
    public void testCaso6_ListarSomenteReceitas() throws IOException {
        Income income = new Income(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(5000.0), IncomeCategory.SALARY);
        Expense expense = new Expense(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(1000.0), ExpenseCategory.SERVICES);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);

        List<Object> listAll = persistence.listRegisterByType(LaunchType.INCOME);
        List<LaunchType> typeRegisters = new ArrayList<LaunchType>();

        for (Object register : listAll) {
            if (register instanceof String[] columns) {
                 LaunchType launchType = LaunchType.fromDescription(columns[0]);
                 typeRegisters.add(launchType);
            }
        }

        assertEquals(1, typeRegisters.size());
        assertEquals(income.getType(), typeRegisters.get(0));
    }

    @Test
    public void testCaso7_ListarSomenteDespesas() throws IOException {
        Income income = new Income(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(5000.0), IncomeCategory.SALARY);
        Expense expense = new Expense(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0), new BigDecimal(1000.0), ExpenseCategory.SERVICES);

        persistence.saveRegister(income, null);
        persistence.saveRegister(null, expense);

        List<Object> listAll = persistence.listRegisterByType(LaunchType.EXPENSE);
        List<LaunchType> typeRegisters = new ArrayList<LaunchType>();

        for (Object register : listAll) {
            if (register instanceof String[] columns) {
                 LaunchType launchType = LaunchType.fromDescription(columns[0]);
                 typeRegisters.add(launchType);
            }
        }

        assertEquals(1, typeRegisters.size());
        assertEquals(expense.getType(), typeRegisters.get(0));
    }
}
