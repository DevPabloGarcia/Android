package garcia.pablo.butterknife.model;

/**
 * Created by V on 23/1/16.
 */
public class Player {

    private String name, surname, nick = "", address = "", email = "", dni;
    private int age, ranking = 0;
    private long id;

    public Player(String name,String surname, String dni,  int age) {
        this.name = name;
        this.dni = dni;
        this.surname = surname;
        this.age = age;
    }

    public Player(String name, String surname, String nick, String address, String email, String dni, int age, int ranking) {
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.address = address;
        this.email = email;
        this.dni = dni;
        this.age = age;
        this.ranking = ranking;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

}
