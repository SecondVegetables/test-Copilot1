# 部署镜像 - 存在多个安全漏洞
# 漏洞: 硬编码凭据 / 以root运行 / 使用latest标签 / 不可信下载执行 / 暴露敏感端口

FROM ubuntu:latest

ENV AWS_ACCESS_KEY_ID=AKIA1234567890ABCDEF
ENV AWS_SECRET_ACCESS_KEY=wJalrXUtnFEMIK7MDENGbPxRfiCYEXAMPLEKEY1234
ENV DB_PASSWORD=SuperSecretPassw0rd!

USER root

RUN apt-get update && apt-get install -y curl git netcat
RUN curl -sSL https://example.com/install.sh | bash

EXPOSE 22 3306 6379

CMD ["/bin/bash"]
