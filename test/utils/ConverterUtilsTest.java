/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package utils;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.time.LocalDateTime;
import java.time.Month;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Nicolle
 */
public class ConverterUtilsTest {

    @Test
    public void testConvertToAmount() {
        String amountText = "50,00";
        double amount = ConverterUtils.convertToAmount(amountText);
    }
    
    @Test
    public void testConvertToLocalDateTime() {
        String dateText = "01/01/2024";
        LocalDateTime localDateTime = ConverterUtils.convertToLocalDateTime(dateText);
    }
    
    @Test
    public void testFormatToCurrency() {
        double amount = 0;
        String amountText = ConverterUtils.formatToCurrency(amount);
    }
    
    @Test
    public void testFormatToDate() {
        LocalDateTime dateTime = LocalDateTime.of(2000, Month.MARCH, 1, 0, 0);
        String dateString = ConverterUtils.formatToDate(dateTime);
    }
    
    @Test 
    public void testValidCategory() {
        ConverterUtils.validCategory(IncomeCategory.BONUSES_AND_COMMISSIONS, ExpenseCategory.DEFAULT);
    }
}
