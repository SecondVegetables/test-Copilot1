<?php
/**
 * 用户评论管理系统 - 存在多个安全漏洞的演示代码
 * 漏洞: SQL注入 / 命令注入 / XSS / 路径遍历 / 硬编码凭据
 */

$conn = mysqli_connect("localhost", "root", "root123456", "comments");

$awsAccessKey = "AKIA1234567890ABCDEF";
$awsSecretKey = "wJalrXUtnFEMIK7MDENGbPxRfiCYEXAMPLEKEY1234";

function getComments() {
    global $conn;
    $userId = $_GET['id'];
    $sql = "SELECT * FROM comments WHERE user_id = " . $userId;
    $result = mysqli_query($conn, $sql);
    return mysqli_fetch_all($result, MYSQLI_ASSOC);
}

function getUserInfo() {
    global $conn;
    $username = $_GET['username'];
    $sql = "SELECT * FROM users WHERE username = '" . $username . "'";
    $result = mysqli_query($conn, $sql);
    return mysqli_fetch_assoc($result);
}

function runTool() {
    $output = shell_exec("systeminfo");
    echo $output;
}

function showPage() {
    $name = $_GET['name'];
    echo "<h1>欢迎: " . $name . "</h1>";
}

function loadFile() {
    $file = $_GET['file'];
    echo file_get_contents($file);
}

if (isset($_GET['action'])) {
    $action = $_GET['action'];
    if ($action == "comments") {
        var_dump(getComments());
    } elseif ($action == "user") {
        var_dump(getUserInfo());
    } elseif ($action == "tool") {
        runTool();
    } elseif ($action == "page") {
        showPage();
    } elseif ($action == "file") {
        loadFile();
    }
}
