package com.geekgao.file;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;

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
        String uploadDir = "/home/geekgao/";
        if (file == null) {
            return INPUT;
        }

        FileInputStream inFile = null;
        FileOutputStream outFle = null;
        try {
            inFile = new FileInputStream(file);
            outFle = new FileOutputStream(new File("/home/geekgao/" + fileFileName));
            byte[] b = new byte[8192];
            int bLength;

            while (-1 != (bLength = inFile.read(b))) {
                outFle.write(b,0,bLength);
            }
        } finally {
            if (inFile != null) {
                inFile.close();
            }
            if (outFle != null) {
                outFle.close();
            }
        }
        return SUCCESS;
    }

    public String downFile() {
        return SUCCESS;
    }
}
