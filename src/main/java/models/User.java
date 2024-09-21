package models;

public class User {

    private String email;
    private String password;
    private String name;
    private String job;

    private User(RegistrationData registrationData) {
        this.email = registrationData.getEmail();
        this.password = registrationData.getPassword();
    }

    private User(UpdateData updateData) {
        this.name = updateData.getName();
        this.job = updateData.getJob();
    }

    public static User createUserForRegistration(String email, String password){
        return new User(new RegistrationData(email, password));
    }

    public static User createUserForUpdate(String name, String job){
        return new User(new UpdateData(name, job));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
            this.job = job;
    }

    }
