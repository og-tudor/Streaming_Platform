package Input;

public class UserInput {
    CredentialsInput credentials;

    public UserInput(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    public UserInput() {
        this.credentials = new CredentialsInput();
    }

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Input.UserInput{" +
                "credentials=" + credentials +
                '}';
    }
}
