# cen4802 - Software Integration, Configuration, and Testing
**Author:** Austin Alber  
**Description:** A Java Spring Boot REST API demonstrating automated build workflows, JUnit 5 unit testing, and automated Continuous Integration (CI) via GitHub Actions.

---

## CI/CD Pipeline Status
* **CI Platform:** GitHub Actions
* **Workflow File:** `.github/workflows/ci.yml`
* **Automated Triggers:** Push to `main`, Pull Requests to `main`
* **Pipeline Lifecycle:** Checkout Code $\rightarrow$ Setup JDK 17 $\rightarrow$ Maven Clean Package (`mvn clean package`) $\rightarrow$ Upload JAR Artifact

---

## Project Structure
* **Source Code:** `src/main/java/com/cen4802/app/`
* **Unit Tests:** `src/test/java/com/cen4802/app/`
* **Build Config:** `pom.xml`
* **CI Config:** `.github/workflows/ci.yml`

---

## Local Build & Execution

```bash
# Run unit tests locally
mvn test

# Package executable fat JAR locally
mvn clean package

# Run packaged application
java -jar target/app-0.0.1-SNAPSHOT.jar