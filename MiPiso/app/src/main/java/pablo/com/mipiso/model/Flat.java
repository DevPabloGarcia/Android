package pablo.com.mipiso.model;

import java.util.ArrayList;

public class Flat {

    private String id;
    private String name;
    private User creator;
    private LatLng latLng;
    private ArrayList<User> tenants;
    private ArrayList<Room> rooms;
    private ArrayList<Room> commonRooms;
    private ArrayList<Task> tasks;
    private Double renting;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public ArrayList<User> getTenants() {
        return tenants;
    }

    public void setTenants(ArrayList<User> tenants) {
        this.tenants = tenants;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Room> getCommonRooms() {
        return commonRooms;
    }

    public void setCommonRooms(ArrayList<Room> commonRooms) {
        this.commonRooms = commonRooms;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Double getRenting() {
        return renting;
    }

    public void setRenting(Double renting) {
        this.renting = renting;
    }
}
