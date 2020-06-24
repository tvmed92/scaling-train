package in.reqres.models;

public class User {

    private String name;
    private String job;
    private String id;

    public User(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getJob() {
        return job;
    }

    public User setJob(String job) {
        this.job = job;
        return this;
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }
}
