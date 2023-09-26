package org.cristiancmello;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
  private final List<BankTransaction> bankTransactions;

  public BankStatementProcessor(List<BankTransaction> bankTransactions) {
    this.bankTransactions = bankTransactions;
  }

  public BigDecimal calculateTotalAmount() {
    var total = BigDecimal.ZERO;

    for (final var bankTransaction : bankTransactions) total = total.add(bankTransaction.getAmount());

    return total;
  }

  public BigDecimal calculateTotalAmountInMonth(final Month month) {
    var total = BigDecimal.ZERO;

    for (final var bankTransaction : bankTransactions)
      if (bankTransaction.getDate().getMonth() == month)
        total = total.add(bankTransaction.getAmount());

    return total;
  }

  public BigDecimal calculateTotalForCategory(final String category) {
    var total = BigDecimal.ZERO;

    for (final var bankTransaction : bankTransactions)
      if (bankTransaction.getDescription().equals(category))
        total = total.add(bankTransaction.getAmount());

    return total;
  }
}
