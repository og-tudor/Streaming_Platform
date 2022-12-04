import java.util.ArrayList;

public class UserInput {
    User credentials;

    public UserInput(User credentials) {
        this.credentials = credentials;
    }

    public UserInput() {
        this.credentials = new User();
    }

    public User getCredentials() {
        return credentials;
    }

    public void setCredentials(User credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
