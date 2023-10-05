package org.cristiancmello;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * BankTransactionDao to Informational Cohesion Example
 */
public class BankTransactionDao {
  // bankTransactions acting as in-memory database
  private final List<BankTransaction> bankTransactions = new ArrayList<>();

  public void save(final LocalDate date, final BigDecimal amount, final String description) {
    bankTransactions.add(new BankTransaction(date, amount, description));
  }

  public List<BankTransaction> all() {
    return bankTransactions;
  }
}
