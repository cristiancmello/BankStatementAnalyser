package org.cristiancmello;

import java.time.Month;

public class BankTransactionAnalyserSimple {
  public void collectSummary(BankStatementProcessor bankStatementProcessor) {
    System.out.printf("The total for all transactions is %.2f", bankStatementProcessor.calculateTotalAmount());
    System.out.print('\n');
    System.out.printf("The total for all transactions in January is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.JANUARY));
    System.out.print('\n');
    System.out.printf("The total for all transactions in February is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.FEBRUARY));
    System.out.print('\n');
    System.out.printf("The total salary received is %.2f", bankStatementProcessor.calculateTotalForCategory("Salary"));
  }
}
