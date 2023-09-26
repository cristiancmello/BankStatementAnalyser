package org.cristiancmello;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

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
}
