package org.example.messagesservice.core.domain;

public class Health {

    private String name;
    private String status;
    private String version;

    public Health() { }

    public Health(String name, String status, String version) {
        this.name = name;
        this.status = status;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
