package org.cristiancmello;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankTransaction {
  private final BigDecimal amount;

  private LocalDate date;

  public BankTransaction(LocalDate date, BigDecimal amount) {
    this.amount = amount;
    this.date = date;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }
}
