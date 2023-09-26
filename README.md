# Bank Statement Analyser

Make your **unit tests** and **integration tests** faster.
Support to build **executable JAR** and **JaCoCo Code Coverage**.

## Dependencies

- **JUnit 5** (`junit-jupiter`) v5.10.0
- **AssertJ** (`assertj-core`) v3.24.2

## Plugins

- **Maven Surefire** (`maven-surefire-plugin`) v3.1.2 : add Unit Tests support
- **Maven Failsafe** (`maven-failsafe-plugin`) v3.1.2 : add Integration Tests support
- **Maven JAR plugin** (`maven-jar-plugin`) v3.3.0 : generate executable JAR
- **Maven JaCoCo plugin** (`jacoco-maven-plugin`) v0.8.10 : add Code Coverage report

## How to Build?

* Execute unit tests before package.

```sh
mvn clean package
```

* Execute without unit tests.

```sh
mvn clean package -DskipTests
```

## How to Run?

* Example

```sh
java -jar target/maven-quickstart-junit5-1.0-SNAPSHOT.jar
Hello world!
```

## How to Test?

This command will execute all Unit Tests and Integration Tests.

```sh 
mvn clean verify
```

### Unit Tests Only

```sh
mvn clean verify -DskipITs
# or
mvn clean test
```

### Integration Tests Only

```sh
mvn clean verify -DskipTests
```

### Skip All Tests

```sh
mvn clean verify -DskipTests -DskipITs
```

## How to Generate Code Coverage Report?

- Verify tests and Generate Report with JaCoCo
- JaCoCo Report path is `target/site/jacoco`

```sh
mvn clean verify jacoco:report
```