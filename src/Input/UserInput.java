package Input;

public class UserInput {
    Credentials credentials;

    public UserInput(Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInput() {
        this.credentials = new Credentials();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Input.UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
