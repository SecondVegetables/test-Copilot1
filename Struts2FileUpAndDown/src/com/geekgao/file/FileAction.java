package com.geekgao.file;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.nio.file.*;

public class FileAction extends ActionSupport {

    private File file;
    private String fileFileName;
    private String fileContentType;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String upFile() throws IOException {
        System.out.println("上传文件:" + fileFileName);
        if (file == null) {
            return INPUT;
        }

        String uploadDir = System.getenv("UPLOAD_DIR") != null ? System.getenv("UPLOAD_DIR") : "/home/geekgao/";
        if (fileFileName == null || fileFileName.isEmpty() || fileFileName.contains("/") || fileFileName.contains("\\")
                || ".".equals(fileFileName) || "..".equals(fileFileName)) {
            return INPUT;
        }

        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Path destination = uploadPath.resolve(fileFileName).normalize();
        if (!destination.startsWith(uploadPath)) {
            return INPUT;
        }

        try (FileInputStream inFile = new FileInputStream(file);
             OutputStream outFle = Files.newOutputStream(destination, StandardOpenOption.CREATE,
                     StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE, LinkOption.NOFOLLOW_LINKS)) {
            byte[] b = new byte[8192];
            int bLength;
            while (-1 != (bLength = inFile.read(b))) {
                outFle.write(b, 0, bLength);
            }
        }
        return SUCCESS;
    }

    public String downFile() {
        return SUCCESS;
    }
}
