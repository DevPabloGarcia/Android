package pablo.com.mipiso.model;

import pablo.com.mipiso.utils.Utils;

public class User {

    private String id;
    private String name;
    private String surname;
    private String phone;
    private User_type type;
    private Task currentTask;
    private Room room;

    public User() {
        this.id = Utils.generateUUID();
    }

    public User(String name, String surname, String phone, User_type type, Task currentTask, Room room) {
        this.id = Utils.generateUUID();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.type = type;
        this.currentTask = currentTask;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User_type getType() {
        return type;
    }

    public void setType(User_type type) {
        this.type = type;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
}
