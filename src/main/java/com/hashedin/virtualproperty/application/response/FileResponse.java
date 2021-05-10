package com.hashedin.virtualproperty.application.response;

public class FileResponse {

    public FileResponse(String url, String id) {
        this.url = url;
        this.id = id;
    }
    public FileResponse(Object url, Object id) {
        this.url = url.toString();
        this.id = id.toString();
    }

    public String url;
    public String id;
}
