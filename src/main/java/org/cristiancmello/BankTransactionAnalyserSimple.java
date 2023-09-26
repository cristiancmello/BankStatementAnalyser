package org.cristiancmello;

import java.math.BigDecimal;

public class BankTransactionAnalyserSimple {
  public void printTotalTransactions() {
    var amount = BigDecimal.valueOf(6820);
    System.out.printf("The total for all transactions is %.2f", amount);
  }
}
