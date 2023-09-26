package org.cristiancmello;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.TextStyle;
import java.util.Locale;

public class BankTransactionAnalyserSimple {
  public void printTotalTransactions() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    final var lines = Files.readAllLines(path);

    var total = BigDecimal.ZERO;

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);

    for (var bankTransaction : bankTransactions) total = total.add(bankTransaction.getAmount());

    System.out.printf("The total for all transactions is %.2f", total);
  }

  public void printTotalTransactionsInJanuary() throws IOException {
    final var bankStatementParser = new BankStatementCsvParser();

    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    var total = BigDecimal.ZERO;

    final var lines = Files.readAllLines(path);

    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);

    for (var bankTransaction : bankTransactions) {
      var bankTransactionMonth = bankTransaction.getDate().getMonth();
      var bankTransactionMonthUsDisplayName = bankTransactionMonth.getDisplayName(TextStyle.FULL, Locale.US);
      if (bankTransactionMonthUsDisplayName.equals("January")) total = total.add(bankTransaction.getAmount());
    }

    System.out.printf("The total for all transactions in January is %.2f", total);
  }
}
