package course.qa.model;

import java.util.StringJoiner;

import static course.qa.model.Role.READER;

public class User extends Person {
    private String username;
    private String password;
    private Role role = READER;
    private String phone;
    private boolean active  = true;

    public User() {
        super();
    }

    public User(String firstName, String lastName, int age, String username, String password) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, int age, String username, String password, Role role,
                String phone, boolean active) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.active = active;
    }

    public User(Long id, String firstName, String lastName, int age, String username, String password,
                Role role, String phone, boolean active) {
        super(id, firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "User [", "]");
        sj.add(super.toString());
        sj.add("username: " + username);
        sj.add("password: " + password);
        sj.add("role: " + role);
        sj.add("phone: " + phone);
        sj.add("active: " + active);
        return sj.toString();
    }

}
