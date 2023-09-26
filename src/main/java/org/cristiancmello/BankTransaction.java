package org.cristiancmello;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BankTransaction {
  private final BigDecimal amount;
  private final LocalDate date;
  private final String description;

  public BankTransaction(LocalDate date, BigDecimal amount, String description) {
    this.amount = amount;
    this.date = date;
    this.description = description;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public String getDescription() {
    return description;
  }
}
