/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.io.IOException;
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
        persistence.createFile("./test/files/financialControlTest.csv");
    }

    @After
    public void tearDown() {
        persistence.getLaunchFile().delete();
    }

    @Test
    public void testCreateIncome() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));
    }


    @Test
    public void testCreateExpense() throws IOException {      
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
    }

    @Test
    public void testListIncome() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        List<Income> listIncomes = FinancialControl.listIncome();
        
        assertNotNull(listIncomes);
        assertEquals(1, listIncomes.size());
    }

    @Test
    public void testListExpense() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        List<Expense> listExpenses = FinancialControl.listExpense();
        
        assertNotNull(listExpenses);
        assertEquals(1, listExpenses.size());
    }

    @Test
    public void testListReleasesByDate() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        List<Launch> listLaunches = FinancialControl.listReleasesOrderByDate();
        
        assertNotNull(listLaunches);
        assertEquals(2, listLaunches.size());
    }

    @Test
    public void testCheckCurrentBalance() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        LocalDateTime dateTime = LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0);
        
        assertEquals(10, FinancialControl.checkCurrentBalance(dateTime), 0);
    }

    @Test
    public void testCheckTotalBalance() throws IOException {
        FinancialControl.createIncome(10, IncomeCategory.OTHER_INCOME, LocalDateTime.of(2024, Month.JANUARY, 1, 0, 0, 0));        
        FinancialControl.createExpense(15, ExpenseCategory.OTHER_EXPENSE, LocalDateTime.of(2024, Month.JANUARY, 2, 0, 0, 0));
        
        assertEquals(-5, FinancialControl.checkTotalBalance(), 0);
    }
}
