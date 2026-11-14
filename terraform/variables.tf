variable "host_port" {
  description = "Loopback-only host port published for the application."
  type        = number
  default     = 18081
}

variable "app_log_level" {
  description = "Application logging threshold passed to the container."
  type        = string
  default     = "INFO"

  validation {
    condition     = contains(["INFO", "DEBUG"], var.app_log_level)
    error_message = "app_log_level must be INFO or DEBUG."
  }
}
