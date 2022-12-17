package input;

public class UserInput {
    private Credentials credentials;

    public UserInput(final Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInput() {
        this.credentials = new Credentials();
    }
    /** Getter */
    public Credentials getCredentials() {
        return credentials;
    }
    /** Setter */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
    /** Prints the credentials of a user */
    @Override
    public String toString() {
        return "Input.UserInput{"
                +
                "credentials=" + credentials
                +
                '}';
    }
}
