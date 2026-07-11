package com.example.office.model;

public class Desk {

    public enum Status { AVAILABLE, BOOKED }

    private String id;
    private String name;
    private String officeRoom;
    private Status status;

    public Desk() {}

    public Desk(String id, String name, String officeRoom, Status status) {
        this.id = id;
        this.name = name;
        this.officeRoom = officeRoom;
        this.status = status;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeRoom() {
        return officeRoom;
    }
    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom;
    }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
