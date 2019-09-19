package task2;

@MyAnnotation
public class MyClassWithAnnotation {
    private String name;
    private String email;

    public MyClassWithAnnotation() {
    }

    public MyClassWithAnnotation(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MyClassWithAnnotation{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
