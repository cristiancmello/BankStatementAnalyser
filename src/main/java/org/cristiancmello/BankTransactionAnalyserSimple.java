package org.cristiancmello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;

public class BankTransactionAnalyserSimple {
  final String filename = "statements.csv";
  final Path path = Paths.get("src/main/resources/" + filename);

  public void printTotalTransactions() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var lines = Files.readAllLines(path);

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);
    var bankStatementProcessor = new BankStatementProcessor(bankTransactions);

    var total = bankStatementProcessor.calculateTotalAmount();

    System.out.printf("The total for all transactions is %.2f", total);
  }

  public void printTotalTransactionsInJanuary() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var lines = Files.readAllLines(path);

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);
    var bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    var total = bankStatementProcessor.calculateTotalAmountInMonth(Month.JANUARY);

    System.out.printf("The total for all transactions in January is %.2f", total);
  }

  public void collectSummary(BankStatementProcessor bankStatementProcessor) {
    System.out.printf("The total for all transactions is %.2f", bankStatementProcessor.calculateTotalAmount());
    System.out.print('\n');
    System.out.printf("The total for all transactions in January is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.JANUARY));
    System.out.print('\n');
    System.out.printf("The total for all transactions in February is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.FEBRUARY));
    System.out.print('\n');
    System.out.print("The total salary received is 6000,00");
  }
}
