package course.stream.sam;

public class Phone implements Comparable<Phone> {
    private PhoneKind kind;
    private String number;

    public Phone() {
    }

    public Phone(PhoneKind kind, String number) {
        this.kind = kind;
        this.number = number;
    }

    public PhoneKind getKind() {
        return kind;
    }

    public void setKind(PhoneKind kind) {
        this.kind = kind;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;

        Phone phone = (Phone) o;

        if (getKind() != phone.getKind()) return false;
        return getNumber().equals(phone.getNumber());
    }

    @Override
    public int hashCode() {
        int result = getKind().hashCode();
        result = 31 * result + getNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "kind=" + kind +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public int compareTo(Phone other) {
        return 0;
    }
}
