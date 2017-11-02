package com.lcw.one.util.utils.http;


import java.io.UnsupportedEncodingException;

public class ResponseContent {
    private String encoding;
    private byte[] contentBytes;
    private int statusCode;
    private String contentType;
    private String contentTypeString;

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentTypeString() {
        return this.contentTypeString;
    }

    public void setContentTypeString(String contenttypeString) {
        this.contentTypeString = contenttypeString;
    }

    public String getContent() {
        return getContent("utf-8");
    }

    public String getContent(String encoding) {
        if (encoding == null) {
            return new String(this.contentBytes);
        }
        try {
            return new String(this.contentBytes, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new String(this.contentBytes);
    }

    public String getUTFContent() {
        return getContent("UTF-8");
    }

    public byte[] getContentBytes() {
        return this.contentBytes;
    }

    public void setContentBytes(byte[] contentBytes) {
        this.contentBytes = contentBytes;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
