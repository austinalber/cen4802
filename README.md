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
```

## Application logging

The application uses Spring Boot's default SLF4J/Logback logging stack. Application
events are written to the console and to the rolling `logs/application.log` file.
Entries include an ISO-style timestamp, severity, thread, logger, and message.
Greeting, status, and midterm requests are recorded at `INFO`; accepted-request
diagnostics are available at `DEBUG`; rejected blank names are recorded at `WARN`
with request context and the exception. User-provided names are not included in
log messages.

Run the application with `.\mvnw.cmd spring-boot:run`, then try:

```powershell
Invoke-RestMethod 'http://localhost:8080/api/greet?name=Taylor'
Invoke-RestMethod 'http://localhost:8080/api/status'
Invoke-RestMethod 'http://localhost:8080/midterm'
Invoke-WebRequest 'http://localhost:8080/api/greet?name='
```

The last request intentionally exercises validation and returns HTTP 400. Examine
the file with `Get-Content .\logs\application.log -Tail 50` or filter it with
`Select-String 'WARN|Greeting generated' .\logs\application.log`. In Kubernetes,
use `kubectl logs deployment/java-app-deployment`; container stdout is the
recommended operational source. The file is a convenient local/short-term rolling
copy (10 MB per file, seven historical files); it is not durable across pod
replacement unless a persistent volume is mounted. A log collector should retain
production logs centrally.

The application package defaults to `INFO`. Set `APP_LOG_LEVEL=DEBUG` or pass
`--logging.level.com.cen4802.app=DEBUG` when starting the application to include
request diagnostics. Restore `INFO` for normal operation to avoid unnecessary
diagnostic volume.

To replay the assignment's 110 real greeting requests in the Spring Boot
integration-test context and generate more than 100 application log entries, run
`.\mvnw.cmd -Dtest=AppApplicationTests#repeatedGreetingRequestsExerciseNormalApplicationLogging test`.
Repeat with `$env:APP_LOG_LEVEL='DEBUG'` set in PowerShell to include the extra
diagnostic events, then remove it to return to the default `INFO` level.

## Local Infrastructure as Code (Terraform)

The `terraform/` directory uses Terraform and the Docker provider to build the
existing Spring Boot Dockerfile and manage a dedicated Docker image, bridge
network, and application container. The recorded baseline published container
port 8080 on localhost port 18080; the committed configuration reflects the
subsequent change to port 18081. Both mappings bind to `127.0.0.1`. Terraform
state and saved plans are excluded from version control; the provider dependency
lockfile is retained for repeatable initialization.

Prerequisites: Docker Desktop running in Linux-container mode, Terraform 1.5 or
newer, and the existing packaged JAR at
`target/app-0.0.1-SNAPSHOT.jar`. Rebuild it with `.\mvnw.cmd package` if needed.
From the repository root in PowerShell, initialize, validate, and preview:

```powershell
Push-Location terraform
terraform init
terraform fmt -check
terraform validate
terraform plan
terraform apply -auto-approve
Pop-Location
```

In PowerShell, if Terraform is installed but not on PATH, invoke
`& "$env:LOCALAPPDATA\Microsoft\WinGet\Packages\Hashicorp.Terraform_Microsoft.Winget.Source_8wekyb3d8bbwe\terraform.exe"`
in place of `terraform` above. The Docker provider uses the default Buildx builder
to build the image from the existing Dockerfile. To reproduce the recorded
sequence from the committed state, temporarily set `host_port` in
`terraform/variables.tf` to `18080` before the initial plan/apply; then change it
to `18081` and plan/apply again. The port update replaces only the app container.
Confirm `/api/status` at each output URL, then remove the assignment-owned
resources with `terraform destroy`. Do not remove resources outside this
Terraform state.

## Included Project Resources

The complete Java source, Maven build configuration, Dockerfile, Kubernetes
manifests, Terraform configuration, and provider lockfile are included in this
repository. The application source and existing deployment assets were reused
from Austin Alber's earlier CEN4802 coursework; the Terraform configuration
was created for this assignment.
