package model;

public enum Role {
    READER("Has basic permissions to view book information"),
    AUTHOR("Can add new books to the system"),
    ADMIN("Can manage users and books");
    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
