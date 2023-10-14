package org.cristiancmello;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Month;

public class BankStatementAnalyserSimple {
  private static final String RESOURCES = "src/main/resources/";

  public void analyze(final String filename, final BankStatementParser bankStatementParser)
    throws IOException
  {
    final var path = Paths.get(RESOURCES + filename);
    final var lines = Files.readAllLines(path);
    var bankTransactions = bankStatementParser.parseLinesFrom(lines);
    var bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    collectSummary(bankStatementProcessor);
  }

  private void collectSummary(BankStatementProcessor bankStatementProcessor) {
    System.out.printf("The total for all transactions is %.2f", bankStatementProcessor.calculateTotalAmount());
    System.out.print('\n');
    System.out.printf("The total for all transactions in January is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.JANUARY));
    System.out.print('\n');
    System.out.printf("The total for all transactions in February is %.2f", bankStatementProcessor.calculateTotalAmountInMonth(Month.FEBRUARY));
    System.out.print('\n');
    System.out.printf("The total salary received is %.2f", bankStatementProcessor.calculateTotalForCategory("Salary"));
  }
}
