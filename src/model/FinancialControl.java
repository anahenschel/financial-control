/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import enums.ExpenseCategory;
import enums.IncomeCategory;
import enums.LaunchType;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
     * @param totalBalance
     * @throws java.io.IOException
     */
    public static void createIncome(BigDecimal amount, IncomeCategory incomeCategory, LocalDateTime dateTime, BigDecimal totalBalance) throws IOException {
        Income income = new Income();
        income.setDateTime(dateTime);
        income.setAmount(amount);
        income.setIncomeCategory(incomeCategory);
        income.setTotalBalance(totalBalance);
        
        persistenceCSVImpl.saveRegister(income, null);
    }

    /**
     * Cria as despesas com base nos parametros recebidos
     *
     * @param amount O valor da despesa
     * @param expenseCategory A categoria da despesa
     * @param dateTime A data e hora da despesa
     * @param totalBalance
     * @throws java.io.IOException
     */
    public static void createExpense(BigDecimal amount, ExpenseCategory expenseCategory, LocalDateTime dateTime, BigDecimal totalBalance) throws IOException {
        Expense expense = new Expense();
        expense.setDateTime(dateTime);
        expense.setAmount(amount);
        expense.setExpenseCategory(expenseCategory);
        expense.setTotalBalance(totalBalance);
        
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
                    BigDecimal totalBalance = new BigDecimal(columns[4]);

                    IncomeCategory incomeCategory = IncomeCategory.fromDescription(columns[1]);
                    Income income = new Income(localDateTime, amount, incomeCategory, totalBalance);

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
                    BigDecimal totalBalance = new BigDecimal(columns[4]);
                    
                    ExpenseCategory expenseCategory = ExpenseCategory.fromDescription(columns[1]);
                    Expense expense = new Expense(localDateTime, amount, expenseCategory, totalBalance);

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
                    LaunchType launchType = LaunchType.valueOf(columns[0]);
                    LocalDateTime localDateTime = ConverterUtils.parseIsoDateTime(columns[2]);
                    BigDecimal amount = new BigDecimal(columns[3]);
                    BigDecimal totalBalance = new BigDecimal(columns[4]);

                    Launch launch;
                    if (launchType == LaunchType.INCOME) {
                        IncomeCategory incomeCategory = IncomeCategory.fromDescription(columns[1]);
                        launch = new Income(localDateTime, amount, incomeCategory, totalBalance);
                    } else if (launchType == LaunchType.EXPENSE) {
                        ExpenseCategory expenseCategory = ExpenseCategory.fromDescription(columns[1]);
                        launch = new Expense(localDateTime, amount, expenseCategory, totalBalance);
                    } else {
                        continue;
                    }

                    listLaunchByFilter.add(launch);
                }
            }

            Collections.sort(listLaunchByFilter);
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
}
