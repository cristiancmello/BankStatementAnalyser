package org.cristiancmello;

import java.math.BigDecimal;
import java.time.Month;
import java.util.ArrayList;
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

  public List<BankTransaction> selectInMonth(final Month month) {
    final var bankTransactionsInMonth = new ArrayList<BankTransaction>();

    for (final var bankTransaction : bankTransactions) {
      if (bankTransaction.getDate().getMonth() == month) bankTransactionsInMonth.add(bankTransaction);
    }

    return bankTransactionsInMonth;
  }
}
