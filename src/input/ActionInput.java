package input;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ActionInput {
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private Filter filters;
    private String movie;
    private String count;
    private Double rate;
    private MovieInput addedMovie;
    private String deletedMovie;
    private String subscribedGenre;

    public ActionInput() {
    }

    /** Getter */
    public String getType() {
        return type;
    }
    /** Setter */
    public void setType(final String type) {
        this.type = type;
    }
    /** Getter */
    public String getPage() {
        return page;
    }
    /** Setter */
    public void setPage(final String page) {
        this.page = page;
    }
    /** Getter */
    public String getFeature() {
        return feature;
    }
    /** Setter */
    public void setFeature(final String feature) {
        this.feature = feature;
    }
    /** Getter */
    public Credentials getCredentials() {
        return credentials;
    }
    /** Setter */
    public void setCredentials(final Credentials credentials) {
        this.credentials = credentials;
    }
    /** Getter */
    public String getStartsWith() {
        return startsWith;
    }
    /** Setter */
    public void setStartsWith(final String startsWith) {
        this.startsWith = startsWith;
    }

    /** Getter */
    public Double getRate() {
        BigDecimal bigDecimal = new BigDecimal(rate).setScale(2, RoundingMode.FLOOR);
        return bigDecimal.doubleValue();
    }
    /** Setter */
    public void setRate(final Double rate) {
        this.rate = rate;
    }
    /** Getter */
    public String getCount() {
        return count;
    }
    /** Setter */
    public void setCount(final String count) {
        this.count = count;
    }
    /** Getter */
    public String getMovie() {
        return movie;
    }
    /** Setter */
    public void setMovie(final String movie) {
        this.movie = movie;
    }
    /** Getter */
    public Filter getFilters() {
        return filters;
    }
    /** Setter */
    public void setFilters(final Filter filters) {
        this.filters = filters;
    }
    /** Getter */
    public String getSubscribedGenre() {
        return subscribedGenre;
    }
    /** Setter */
    public void setSubscribedGenre(String subscribedGenre) {
        this.subscribedGenre = subscribedGenre;
    }
    /** Getter */
    public MovieInput getAddedMovie() {
        return addedMovie;
    }
    /** Setter */
    public void setAddedMovie(MovieInput addedMovie) {
        this.addedMovie = addedMovie;
    }
    /** Getter */
    public String getDeletedMovie() {
        return deletedMovie;
    }
    /** Setter */
    public void setDeletedMovie(String deletedMovie) {
        this.deletedMovie = deletedMovie;
    }

    /** Prints an action */
    @Override
    public String toString() {
        return "ActionInput{"
                +
                "type='" + type + '\''
                +
                ", page='" + page + '\''
                +
                ", feature='" + feature + '\''
                +
                ", credentials=" + credentials
                +
                ", startsWith='" + startsWith + '\''
                +
                ", filters=" + filters
                +
                ", movie='" + movie + '\''
                +
                ", count='" + count + '\''
                +
                ", rate=" + rate
                +
                ", subscribedGenre='" + subscribedGenre + '\''
                +
                '}';
    }
}

