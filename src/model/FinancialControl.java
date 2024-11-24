/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import enums.LaunchType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import utils.ConverterUtils;

/**
 *
 * @author lucas
 */
public class FinancialControl {

    private static final PersistenceCSVImpl persistenceCSVImpl = PersistenceCSVImpl.getPersistenceCSVImpl();

    /**
     * Cria as receitas com base nos parametros recebidos
     *
     * @param amount O valor da receita
     * @param incomeCategory A categoria da receita
     * @param dateTime A data e hora da receita
     * @throws java.io.IOException
     */
    public static void createIncome(BigDecimal amount, IncomeCategory incomeCategory, LocalDateTime dateTime) throws IOException {
        Income income = new Income();
        income.setDateTime(dateTime);
        income.setAmount(amount);
        income.setIncomeCategory(incomeCategory);
        
        persistenceCSVImpl.saveRegister(income, null);
    }

    /**
     * Cria as despesas com base nos parametros recebidos
     *
     * @param amount O valor da despesa
     * @param expenseCategory A categoria da despesa
     * @param dateTime A data e hora da despesa
     * @throws java.io.IOException
     */
    public static void createExpense(BigDecimal amount, ExpenseCategory expenseCategory, LocalDateTime dateTime) throws IOException {
        Expense expense = new Expense();
        expense.setDateTime(dateTime);
        expense.setAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        
        persistenceCSVImpl.saveRegister(null, expense);
    }

    /**
     * Lista todas as receitas armazenadas.
     *
     * @return Uma lista de objetos Income representando todas as receitas.
     * @throws java.io.IOException
     */
    public static List<Income> listIncome() throws IOException {
        List<Income> listIncome = new ArrayList<>();

        try {
            List<Object> listRegisters = persistenceCSVImpl.listRegisterByType(LaunchType.INCOME);

            for (Object register : listRegisters) {
                if (register instanceof String[] columns) {
                    LocalDateTime localDateTime = ConverterUtils.parseIsoDateTime(columns[2]);
                    BigDecimal amount = new BigDecimal(columns[3]);

                    IncomeCategory incomeCategory = IncomeCategory.fromDescription(columns[1]);
                    Income income = new Income(localDateTime, amount, incomeCategory);

                    listIncome.add(income);
                }
            }

            Collections.sort(listIncome);
        } catch (IOException ex) {
            throw new IOException("Erro ao listar os registros!");
        }
        return listIncome;
    }

    /**
     * Lista todas as despesas armazenadas.
     *
     * @return Uma lista de objetos Expense representando todas as despesas.
     * @throws java.io.IOException
     */
    public static List<Expense> listExpense() throws IOException {
        List<Expense> listExpense = new ArrayList<>();

        try {
            List<Object> listRegisters = persistenceCSVImpl.listRegisterByType(LaunchType.EXPENSE);

            for (Object register : listRegisters) {
                if (register instanceof String[] columns) {
                    LocalDateTime localDateTime = ConverterUtils.parseIsoDateTime(columns[2]);
                    BigDecimal amount = new BigDecimal(columns[3]);
                    
                    ExpenseCategory expenseCategory = ExpenseCategory.fromDescription(columns[1]);
                    Expense expense = new Expense(localDateTime, amount, expenseCategory);

                    listExpense.add(expense);
                }
            }

            Collections.sort(listExpense);
        } catch (IOException ex) {
            throw new IOException("Erro ao listar os registros!");
        }

        return listExpense;
    }

    /**
     * Lista todos os lançamentos ordenados por data.
     *
     * @return Uma lista de objetos Launch que correspondem ao filtro de data e
     * hora especificado.
     * @throws java.io.IOException
     */
    public static List<Launch> listReleasesOrderByDate() throws IOException {
        List<Launch> listLaunchByFilter = new ArrayList<>();

        try {
            List<Object> listRegisters = persistenceCSVImpl.listRegisterByType(LaunchType.ALL);

            for (Object register : listRegisters) {
                if (register instanceof String[] columns) {
                    LaunchType launchType = LaunchType.fromDescription(columns[0]);
                    LocalDateTime localDateTime = ConverterUtils.parseIsoDateTime(columns[2]);
                    BigDecimal amount = new BigDecimal(columns[3]);

                    Launch launch;
                    if (launchType == LaunchType.INCOME) {
                        IncomeCategory incomeCategory = IncomeCategory.fromDescription(columns[1]);
                        launch = new Income(localDateTime, amount, incomeCategory);
                    } else if (launchType == LaunchType.EXPENSE) {
                        ExpenseCategory expenseCategory = ExpenseCategory.fromDescription(columns[1]);
                        launch = new Expense(localDateTime, amount, expenseCategory);
                    } else {
                        continue;
                    }

                    listLaunchByFilter.add(launch);
                }
            }

            listLaunchByFilter.sort(Comparator.comparing(Launch::getDateTime));
        } catch (IOException ex) {
            throw new IOException("Erro ao listar os registros!");
        }

        return listLaunchByFilter;
    }

    /**
     * Verifica o saldo atual em uma data e hora específica.
     *
     * @param dateTime A data e hora para a qual o saldo atual será verificado.
     * @return O saldo atual como um valor double para a data e hora
     * especificada.
     * @throws java.io.IOException
     */
    public static BigDecimal checkCurrentBalance(LocalDateTime dateTime) throws IOException {
        BigDecimal currentBalance = BigDecimal.ZERO;

        try {
            List<Launch> listReleases = listReleasesOrderByDate();

            List<Launch> filteredReleases = listReleases.stream()
                .filter(launch -> !launch.getDateTime().toLocalDate().isAfter(dateTime.toLocalDate()))
                .collect(Collectors.toList());

            for (Launch launch : filteredReleases) {
                if (launch.getType() == LaunchType.EXPENSE) {
                    currentBalance = currentBalance.subtract(launch.getAmount());
                } else if (launch.getType() == LaunchType.INCOME) {
                    currentBalance = currentBalance.add(launch.getAmount());
                }
            }
        } catch (IOException ex) {
            throw new IOException("Erro ao obter o saldo atual!");
        }

        return currentBalance;
    }

    /**
     * Verifica o saldo total acumulado.
     *
     * @return O saldo total acumulado como um valor double.
     * @throws java.io.IOException
     */
    public static BigDecimal checkTotalBalance() throws IOException {
        BigDecimal totalBalance = BigDecimal.ZERO;

        try {
            List<Launch> listReleases = listReleasesOrderByDate();

            for (Launch launch : listReleases) {
                if (launch.getType() == LaunchType.EXPENSE) {
                    totalBalance = totalBalance.subtract(launch.getAmount());
                } else if (launch.getType() == LaunchType.INCOME) {
                    totalBalance = totalBalance.add(launch.getAmount());
                }
            }
        } catch (IOException ex) {
            throw new IOException("Erro ao obter o saldo total!");
        }

        return totalBalance;
    }
    
    /**
     * Salva uma cópia de um arquivo CSV contendo lançamentos em um diretório especificado pelo usuário.
     * O arquivo gerado terá o nome no formato "lancamentos-dd-MM-yyyy-HH-mm-ss.csv", onde o sufixo 
     * é a data e hora atuais.
     * 
     * @param fileChooser O componente JFileChooser usado para selecionar o diretório de destino.
     * 
     * @throws FileNotFoundException Se o arquivo de origem ou o diretório nao forem encontrados.
     * @throws IOException Se ocorrer um erro de entrada/saída durante o processo de leitura ou escrita do arquivo.
     */
    public static void exportSaveFile(File selectedDirectory) throws FileNotFoundException, IOException {
        File sourceFile = PersistenceCSVImpl.getPersistenceCSVImpl().getLaunchFile();
        File directory = selectedDirectory;
        
        if (!directory.exists()) {
            throw new FileNotFoundException("O sistema não pode encontrar o caminho especificado");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss");
        String currentDate = sdf.format(new Date());

        File destinationFile = new File(directory, "lancamentos-" + currentDate + ".csv");

        try (FileInputStream fis = new FileInputStream(sourceFile);
            FileOutputStream fos = new FileOutputStream(destinationFile)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
        }    
    }
}
