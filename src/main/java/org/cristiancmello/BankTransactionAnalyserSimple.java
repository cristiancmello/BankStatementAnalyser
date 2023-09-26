package org.cristiancmello;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankTransactionAnalyserSimple {
  final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public void printTotalTransactions() throws IOException {
    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    final var lines = Files.readAllLines(path);

    var total = BigDecimal.ZERO;

    var bankTransactions = parseLinesFromCsv(lines);

    for (var bankTransaction : bankTransactions) total = total.add(bankTransaction.getAmount());

    System.out.printf("The total for all transactions is %.2f", total);
  }

  private BankTransaction parseFromCsv(final String line) {
    final var columns = line.split(",");
    final var date = LocalDate.parse(columns[0], DATE_PATTERN);
    final var amount = new BigDecimal(columns[1]);

    return new BankTransaction(date, amount);
  }

  private List<BankTransaction> parseLinesFromCsv(final List<String> lines) {
    final List<BankTransaction> bankTransactions = new ArrayList<>();
    for (final var line: lines) bankTransactions.add(parseFromCsv(line));

    return bankTransactions;
  }

  public void printTotalTransactionsInJanuary() throws IOException {
    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    var total = BigDecimal.ZERO;

    final var lines = Files.readAllLines(path);

    var bankTransactions = parseLinesFromCsv(lines);

    for (var bankTransaction : bankTransactions) {
      var bankTransactionMonth = bankTransaction.getDate().getMonth();
      var bankTransactionMonthUsDisplayName = bankTransactionMonth.getDisplayName(TextStyle.FULL, Locale.US);
      if (bankTransactionMonthUsDisplayName.equals("January")) total = total.add(bankTransaction.getAmount());
    }

    System.out.printf("The total for all transactions in January is %.2f", total);
  }
}
