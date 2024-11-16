/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.swing.JTextField;

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
    public static double convertToAmount(String amount) throws IllegalArgumentException, NumberFormatException {
        double convertedAmount = 0;
        try {
            String normalizedAmount = amount.replace(".", "").replace(",", ".");
            convertedAmount = Double.parseDouble(normalizedAmount);
            
            if (convertedAmount <= 0) {
                throw new IllegalArgumentException("Por favor, informe um valor maior do que zero.");
            }
            
            if (!isValidAmount(normalizedAmount)) {
                throw new IllegalArgumentException("Por favor, informe um valor com até 15 caracteres antes do separador decimal e no máximo 2 caracteres após o separador");
            }
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Por favor, informe um valor numérico válido.");
        }
        
        return convertedAmount;
    }
    
    /**
     * Converte uma string em um objeto LocalDateTime usando o formato 
     * especificado no formatter.
     *
     * @param date A string representando a data a ser convertida.
     * @return O objeto LocalDateTime correspondente.
     * @throws IllegalArgumentException se a data não for válida ou se a conversão falhar.
     */
    public static LocalDateTime convertToLocalDateTime(String date) throws DateTimeParseException, IllegalArgumentException {
        LocalDateTime localDateTime;
        try {
            LocalDate localDate = LocalDate.parse(date, formatter);
            localDateTime = localDate.atTime(LocalTime.now());

            if (!isValidDateTime(localDateTime)) {
                throw new IllegalArgumentException("A data mínima aceita é 01/01/1900. Por favor, insira uma data válida.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Por favor, informe uma data válida.");
        }
        
        return localDateTime;
    }
    
    /**
     * Faz o parsing de uma string de data e hora no formato ISO 8601 para um objeto LocalDateTime.
     *
     * @param date a string de data e hora a ser convertida no formato ISO 8601
     * @return um objeto LocalDateTime representando a data e hora convertida
     * @throws IllegalArgumentException se a string de entrada não estiver em um formato ISO 8601 válido
     */
    public static LocalDateTime parseIsoDateTime(String date) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(date);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Por favor, informe uma data válida no formato ISO 8601 (ex: 2024-01-22T09:52:52.598782400)", date, 0);
        }
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
    
    /**
     * Verifica se a string fornecida representa um valor válido entre 1 a 15 caracteres
     * antes do separador decimal e entre 1 a 2 caracteres após o separador.
     * A string está considerando como separador decimal o ponto (.) ou virgula (,).
     *
     * @param amountText A string representando o valor.
     * @return true se o valor for válido; caso contrário, false.
     */
    private static boolean isValidAmount(String amountText) {
        return amountText != null && amountText.matches("\\d{1,15}([.,]\\d{1,2})?");
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
     * Formats the input of a JTextField as a currency value.
     * 
     * @param jAmount the JTextField where the currency input is being typed.
     * @param evt KeyEvent triggered by the user's key press.
     * @throws NumberFormatException if the text in the JTextField cannot be 
     *         parsed into a numeric value.
     */
    public static void formatAmountInput(JTextField jAmount, KeyEvent evt) throws NumberFormatException{
        String text = jAmount.getText().replace(".", "").replace(",", "");

        if (text.length() >= 18) {
            text = text.substring(1);
        }

        text += evt.getKeyChar();
        text = String.format("%017d", Long.parseLong(text));

        String formattedText = String.format("%03d.%03d.%03d.%03d.%03d,%02d",
            Integer.parseInt(text.substring(0, 3)),
            Integer.parseInt(text.substring(3, 6)),
            Integer.parseInt(text.substring(6, 9)),
            Integer.parseInt(text.substring(9, 12)),
            Integer.parseInt(text.substring(12, 15)),
            Integer.parseInt(text.substring(15, 17))
        );

        jAmount.setText(formattedText);
        jAmount.setCaretPosition(jAmount.getText().length());
    }
}
