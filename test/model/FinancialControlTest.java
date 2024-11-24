/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Nicolle
 */
public class FinancialControlTest {
    
    PersistenceCSVImpl persistence;
    
    @Before
    public void setUp() throws IOException {
        persistence = PersistenceCSVImpl.getPersistenceCSVImpl();
        persistence.createFile("./test/model/financialControlTest.csv");
    }

    @After
    public void tearDown() {
        persistence.getLaunchFile().delete();
    }

    @Test
    public void testCreateIncome() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath()); 
        assertEquals("INCOME;Outras Receitas;2024-01-01T00:00;10", linhas.get(1));
    }


    @Test
    public void testCreateExpense() throws IOException {      
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        File file = persistence.getLaunchFile();
        List<String> linhas = Files.readAllLines(file.toPath());
        assertEquals("EXPENSE;Outras Despesas;2024-01-02T00:00;15", linhas.get(1));
    }

    @Test
    public void testListIncome() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0));  
        
        List<Income> listIncomes = FinancialControl.listIncome();
        
        assertEquals(2, listIncomes.size());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0), listIncomes.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0), listIncomes.get(1).getDateTime());
    }

    @Test
    public void testListExpense() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0));
        
        List<Expense> listExpenses = FinancialControl.listExpense();
        
        assertEquals(2, listExpenses.size());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 3, 0, 0, 0), listExpenses.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0), listExpenses.get(1).getDateTime());
    }

    @Test
    public void testListReleasesByDate() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        List<Launch> listLaunches = FinancialControl.listReleasesOrderByDate();
        
        assertEquals(2, listLaunches.size());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0), listLaunches.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0), listLaunches.get(1).getDateTime());
    }

    @Test
    public void testCheckCurrentBalance() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        LocalDateTime dateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0);
        
        assertEquals(new BigDecimal(10), FinancialControl.checkCurrentBalance(dateTime));
    }

    @Test
    public void testCheckTotalBalance() throws IOException {
        FinancialControl.createIncome(new BigDecimal(10), IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(new BigDecimal(15), ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        assertEquals(new BigDecimal(-5), FinancialControl.checkTotalBalance());
    }
}
