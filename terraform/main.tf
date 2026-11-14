terraform {
  required_version = ">= 1.5.0"

  required_providers {
    docker = {
      source  = "kreuzwerker/docker"
      version = "~> 3.6.2"
    }
  }
}

provider "docker" {
  host = "npipe:////./pipe/dockerDesktopLinuxEngine"
}

resource "docker_image" "application" {
  name         = "cen4802-iac:1.0"
  keep_locally = false

  build {
    context    = "${path.module}/.."
    dockerfile = "${path.module}/../Dockerfile"
    builder    = "default"
  }
}

resource "docker_network" "application" {
  name   = "cen4802-iac-network"
  driver = "bridge"
}

resource "docker_container" "application" {
  name    = "cen4802-iac-app"
  image   = docker_image.application.image_id
  restart = "unless-stopped"
  env     = ["APP_LOG_LEVEL=${var.app_log_level}"]

  networks_advanced {
    name = docker_network.application.name
  }

  ports {
    internal = 8080
    external = var.host_port
    ip       = "127.0.0.1"
  }
}
