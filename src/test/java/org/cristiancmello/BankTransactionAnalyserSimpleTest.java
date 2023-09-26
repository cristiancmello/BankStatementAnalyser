package org.cristiancmello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BankTransactionAnalyserSimpleTest {
  final String filename = "statements.csv";
  final Path path = Paths.get("src/main/resources/" + filename);

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

  @Test
  public void canPrintTotalTransactionInJanuary() throws Exception {
    var totalTransactionsOutput = "The total for all transactions in January is -150,00";

    var bankTransactionsAnalyserSimple = new BankTransactionAnalyserSimple();
    bankTransactionsAnalyserSimple.printTotalTransactionsInJanuary();

    Assertions.assertEquals(outContent.toString(), totalTransactionsOutput);
  }

  @Test
  public void canCollectSummary() throws IOException {
    var totalForAllTransactionsLine = "The total for all transactions is 6820,00";
    var totalForTransactionsInJanuaryLine = "The total for all transactions in January is -150,00";
    var totalForTransactionsInFebruaryLine = "The total for all transactions in February is 6970,00";
    var totalSalaryReceivedLine = "The total salary received is 6000,00";

    final var bankStatementParser = new BankStatementCsvParser();
    final var lines = Files.readAllLines(path);
    var bankTransactions = bankStatementParser.parseLinesFromCsv(lines);

    var bankTransactionsAnalyserSimple = new BankTransactionAnalyserSimple();
    var bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    bankTransactionsAnalyserSimple.collectSummary(bankStatementProcessor);

    Assertions.assertEquals(outContent.toString(), totalForAllTransactionsLine + '\n'
      + totalForTransactionsInJanuaryLine + '\n'
      + totalForTransactionsInFebruaryLine + '\n'
      + totalSalaryReceivedLine
    );
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

Corner Case Questions (Ch 03):
- If the csv file is empty?
- What if value analysis fails 'cause some data is corrupted?
- What if statement line is empty?

Applying SRP
- Read Input
- Analyse the input in specific format
- process result
- report result

Extracting parseFromCsv and parseLinesFromCsv methods from BankTransactionAnalyserSimple
Extracting BankStatementCsvParser from BankTransactionAnalyserSimple
Extracting filename and path as class field from BankTransactionAnalyserSimple
...

+ more recently
> 00f7afa3e795277fd97b47e4ec40b56009537ce3
> c52f8c57f3e3a0baedb3d3eed2974a19ef9535ab
> 26d82d703d363f92bb71d51cab813ed80a66a169
> 9464138130904b1b1d3ad379c69a9d6a2a01c9af
> 2dda59dae1e2a3700a7315fd1d7fafacaceeac81
> d1fd96e88a249edc789b2dd33db1926df52a7216
> 8c86f16564dd3ba3d880da2983bdfae8d5624253
> 9099a071fb811fa23cd5df1a6f56eeffa1fe3454
> f8b3d40b24deeff37a0b644be2a5fe2825cab067
> 6946d317afc26c595cfdcafca43927d0fd8a1eab
- older

1. Total profit and loss in a list of bank statements [DONE]
2. Total transaction in a specific month
3. Top 10 expenses
4. Category where most money is spent
*/