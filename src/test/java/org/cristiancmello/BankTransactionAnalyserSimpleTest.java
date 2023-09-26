package org.cristiancmello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BankTransactionAnalyserSimpleTest {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void canPrintTotalTransaction() throws Exception {
    var totalTransactionsOutput = "The total for all transactions is 6820,00";

    var bankTransactionsAnalyserSimple = new BankTransactionAnalyserSimple();
    bankTransactionsAnalyserSimple.printTotalTransactions();

    Assertions.assertEquals(outContent.toString(), totalTransactionsOutput);
  }
}

/* TODO
Select [1] because KISS: Keep It Short and Simple and there's no requirement.

CSV:
30-01-2017,-100,Deliveroo
30-01-2017,-50,Tesco
01-02-2017,6000,Salary
02-02-2017,2000,Royalties
02-02-2017,-4000,Rent
03-02-2017,3000,Tesco
05-02-2017,-30,Cinema

+ more recently
> f8b3d40b24deeff37a0b644be2a5fe2825cab067
> 6946d317afc26c595cfdcafca43927d0fd8a1eab
- older

1. Total profit and loss in a list of bank statements
2. Total transaction in a specific month
3. Top 10 expenses
4. Category where most money is spent
*/