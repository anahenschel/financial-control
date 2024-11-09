/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author Nicolle
 */
public class ConverterUtils {
    private static final LocalDateTime MIN_DATE = LocalDateTime.of(1900, Month.JANUARY, 1, 0, 0);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    /**
     * Converte uma string em um valor do tipo double.
     *
     * @param amount A string representando o valor a ser convertido.
     * @return O valor convertido para o tipo double.
     * @throws IllegalArgumentException se amount for inválido.
     */
    public static double convertToAmount(String amount) throws IllegalArgumentException {
        if (isValidAmount(amount)) {
            return Double.parseDouble(amount.replace(",", "."));
        }
        
        throw new IllegalArgumentException("Por favor, informe um valor válido");
    }
    
    /**
     * Converte uma string em um objeto LocalDateTime usando o formato 
     * especificado no formatter.
     *
     * @param date A string representando a data a ser convertida.
     * @return O objeto LocalDateTime correspondente.
     * @throws IllegalArgumentException se a data não for válida ou se a conversão falhar.
     */
    public static LocalDateTime convertToLocalDateTime(String date) {
        LocalDateTime localDateTime;
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            localDateTime = localDate.atTime(LocalTime.now());
            
            if (!isValidDateTime(localDateTime)) {
                throw new IllegalArgumentException("Por favor, informe uma data válida");
            }
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new IllegalArgumentException("Por favor, informe uma data válida");
        }
        
        return localDateTime;
    }
    
    /**
     * Verifica a validade das categorias de receita e despesa.
     *
     * @param incCat A categoria de receita, representada por um objeto IncomeCategory.
     * @param expCat A categoria de despesa, representada por um objeto ExpenseCategory.
     * @throws IllegalArgumentException se ambas as categorias forem null ou tiverem o valor DEFAULT.
     */
    public static void validCategory(IncomeCategory incCat, ExpenseCategory expCat) {
        if ((incCat == null || incCat == IncomeCategory.DEFAULT) && (expCat == null || expCat == ExpenseCategory.DEFAULT)) {
            throw new IllegalArgumentException("Por favor, selecione o tipo da transação");
        }
    }
    
    /**
     * Verifica se a string fornecida representa um valor válido.
     *
     * @param amountText A string representando o valor.
     * @return true se o valor for válido; caso contrário, false.
     */
    private static boolean isValidAmount(String amountText) {
        return amountText != null && amountText.matches("\\d+(\\.\\d{1,2})?");
    }
    
    /**
     * Verifica se a data e hora fornecida é válida.
     *
     * @param dateTime A data e hora a ser verificada.
     * @return true se a data e hora for válida; caso contrário, false.
     */
    private static boolean isValidDateTime(LocalDateTime dateTime) {
        return dateTime != null && !dateTime.isBefore(MIN_DATE);
    }
    
    /**
     * Formata um valor do tipo double no padrão de moeda, de acordo com a localidade brasileira.
     *
     * @param amount O valor numérico que será formatado.
     * @return Uma string representando o valor formatado no padrão de moeda brasileira.
     * @throws ArithmeticException Erro ao formatar o valor
     */
    public static String formatToCurrency(double amount) throws ArithmeticException {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return currencyFormat.format(amount);
    }
    
    /**
     * Formata um objeto LocalDateTime para uma string no formato "dd/MM/yyyy".
     * 
     * @param dateTime O objeto LocalDateTime a ser formatado.
     * @return Uma string representando a data no formato "dd/MM/yyyy".
     */
    public static String formatToDate(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
