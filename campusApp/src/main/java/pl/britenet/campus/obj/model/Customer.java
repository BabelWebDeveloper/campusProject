package pl.britenet.campus.obj.model;

public class Customer {

    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;

    private String password;

    public Customer(int id) {
        this.id = id;
    }
    public Customer() {
        this.id = 0;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        try {
            return String.format("first name: %s, last name: %s, email: %s, address: %s, password: %s", first_name, last_name, email, address, password);
        } catch (NullPointerException e){
            return null;
        }

    }
}
