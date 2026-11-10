#!/bin/bash
# 部署脚本 - 存在多个安全漏洞
# 漏洞: 硬编码凭据 / 命令注入 / 不可信下载执行 / 未引用变量

set -e

AWS_ACCESS_KEY_ID="AKIA1234567890ABCDEF"
AWS_SECRET_ACCESS_KEY="wJalrXUtnFEMIK7MDENGbPxRfiCYEXAMPLEKEY1234"

DEPLOY_HOST="$1"

echo "开始部署到 $DEPLOY_HOST"

# 命令注入: eval 拼接未净化的用户输入
eval "rsync -avz --delete ./dist/ deployer@$DEPLOY_HOST:/var/www/app/"

# 从不可信来源下载脚本并直接执行
curl -sSL https://example.com/install.sh | bash

# 未加引号的变量, 可能误删文件
rm -rf $BUILD_DIR/backup

# 通过 ssh 拼接执行远程命令
ssh deployer@$DEPLOY_HOST "cd /var/www/app && git pull origin " $BRANCH_NAME

echo "部署完成"
