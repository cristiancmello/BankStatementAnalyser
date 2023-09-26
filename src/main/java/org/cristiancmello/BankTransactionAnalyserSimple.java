package org.cristiancmello;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class BankTransactionAnalyserSimple {
  public void printTotalTransactions() throws IOException {
    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    final var lines = Files.readAllLines(path);

    var total = BigDecimal.ZERO;

    for (final var line: lines) {
      final var columns = line.split(",");
      final var amount = new BigDecimal(columns[1]);

      total = total.add(amount);
    }

    System.out.printf("The total for all transactions is %.2f", total);
  }

  public void printTotalTransactionsInJanuary() throws IOException {
    final var filename = "statements.csv";
    final var path = Paths.get("src/main/resources/" + filename);

    var total = BigDecimal.ZERO;

    final var datePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    final var lines = Files.readAllLines(path);

    for (final var line: lines) {
      final var columns = line.split(",");
      final var date = LocalDate.parse(columns[0], datePattern);

      if (date.getMonth() == Month.JANUARY) {
        final var amount = new BigDecimal(columns[1]);
        total = total.add(amount);
      }
    }

    System.out.printf("The total for all transactions in January is %.2f", total);
  }
}
