package org.cristiancmello;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyserSimple {
  final String filename = "statements.csv";
  final Path path = Paths.get("src/main/resources/" + filename);

  public void printTotalTransactions() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var lines = Files.readAllLines(path);

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);

    var total = calculateTotalAmount(bankTransactions);

    System.out.printf("The total for all transactions is %.2f", total);
  }

  public void printTotalTransactionsInJanuary() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var lines = Files.readAllLines(path);

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);

    var transactionsInMonth = selectInMonth(bankTransactions, Month.JANUARY);
    var total = calculateTotalAmount(transactionsInMonth);

    System.out.printf("The total for all transactions in January is %.2f", total);
  }

  private BigDecimal calculateTotalAmount(final List<BankTransaction> bankTransactions) {
    var total = BigDecimal.ZERO;

    for (final var bankTransaction : bankTransactions) total = total.add(bankTransaction.getAmount());

    return total;
  }

  private List<BankTransaction> selectInMonth(final List<BankTransaction> bankTransactions, final Month month) {
    final var bankTransactionsInMonth = new ArrayList<BankTransaction>();

    for (final var bankTransaction : bankTransactions) {
      if (bankTransaction.getDate().getMonth() == month) bankTransactionsInMonth.add(bankTransaction);
    }

    return bankTransactionsInMonth;
  }
}
