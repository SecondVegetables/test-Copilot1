# AWS 基础设施定义 - 存在多个安全漏洞
# 漏洞: 硬编码凭据 / S3桶公开读写 / 数据库密码硬编码且公网可达 / 安全组全开

terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 4.0"
    }
  }
}

provider "aws" {
  region     = "us-east-1"
  access_key = "AKIA1234567890ABCDEF"
  secret_key = "wJalrXUtnFEMIK7MDENGbPxRfiCYEXAMPLEKEY1234"
}

resource "aws_s3_bucket" "uploads" {
  bucket = "app-uploads-bucket"
  acl    = "public-read"
}

resource "aws_s3_bucket_object" "uploaded_file" {
  bucket = aws_s3_bucket.uploads.id
  key    = "profile_pic.jpg"
  source = "profile_pic.jpg"
  acl    = "public-read"
}

resource "aws_db_instance" "main" {
  engine             = "mysql"
  instance_class     = "db.t3.micro"
  allocated_storage  = 20
  username           = "admin"
  password           = "P@ssw0rd12345"
  publicly_accessible = true
}

resource "aws_security_group" "web" {
  name = "web-sg"

  ingress {
    from_port   = 0
    to_port     = 65535
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}
