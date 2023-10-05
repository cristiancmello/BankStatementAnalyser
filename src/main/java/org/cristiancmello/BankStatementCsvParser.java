package org.cristiancmello;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCsvParser {
  final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  private BankTransaction parseFrom(final String line) {
    final var columns = line.split(",");
    final var date = LocalDate.parse(columns[0], DATE_PATTERN);
    final var amount = new BigDecimal(columns[1]);
    final var description = columns[2];

    return new BankTransaction(date, amount, description);
  }

  public List<BankTransaction> parseLinesFrom(final List<String> lines) {
    final List<BankTransaction> bankTransactions = new ArrayList<>();
    for (final var line: lines) bankTransactions.add(parseFrom(line));

    return bankTransactions;
  }
}
