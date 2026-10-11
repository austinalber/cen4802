# cen4802 - Software Integration, Configuration, and Testing
**Author:** Austin Alber  
**Description:** A Java Spring Boot REST API built to demonstrate automated build workflows, JUnit 5 automated unit testing, and continuous integration practices.

---

## Project Structure
* **Application Source Code:** `src/main/java/com/cen4802/app/`
* **Unit Test Source Code:** `src/test/java/com/cen4802/app/`
* **Build Configuration:** `pom.xml`
* **Generated Artifact:** `target/app-0.0.1-SNAPSHOT.jar`

---

## Automated Build & Testing Workflow

### 1. Run Unit Tests Only
To execute the automated JUnit 5 test suite independently:

```bash
mvn test