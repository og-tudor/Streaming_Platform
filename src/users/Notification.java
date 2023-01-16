package users;

public class Notification {
    private String movieName;
    private String message;

    public Notification() {
    }

    public Notification(final String movieName, final String message) {
        this.movieName = movieName;
        this.message = message;
    }

    public Notification(final Notification notification) {
        this.movieName = notification.getMovieName();
        this.message = notification.getMessage();
    }

    /** Getter */
    public String getMovieName() {
        return movieName;
    }
    /** Setter */
    public void setMovieName(final String movieName) {
        this.movieName = movieName;
    }
    /** Getter */
    public String getMessage() {
        return message;
    }
    /** Setter */
    public void setMessage(final String message) {
        this.message = message;
    }
}
