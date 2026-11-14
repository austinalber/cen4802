output "application_url" {
  description = "Local URL for the Spring Boot application."
  value       = "http://127.0.0.1:${var.host_port}"
}

output "container_name" {
  description = "Name of the Terraform-managed application container."
  value       = docker_container.application.name
}

output "network_name" {
  description = "Name of the Terraform-managed Docker bridge network."
  value       = docker_network.application.name
}
