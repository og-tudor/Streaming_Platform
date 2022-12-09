package Input;

import java.util.ArrayList;

public class ActionInput {
//          "type": "on page",
//          "page": "login",
//          "feature": "login",
//          "credentials": {
//        "name": "Eduard",
//        "password": "secret"
    private String type;
    private String page;
    private String feature;
    private Credentials credentials;
    private String startsWith;
    private Filter filters;
    private String movie;
    private String count;
    private int rate;


//    public Input.Action(String type, String page, String feature) {
//        this.type = type;
//        this.page = page;
//        this.feature = feature;
//    }

    public ActionInput() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public String getStartsWith() {
        return startsWith;
    }

    public void setStartsWith(String startsWith) {
        this.startsWith = startsWith;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public Filter getFilters() {
        return filters;
    }

    public void setFilters(Filter filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "ActionInput{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                ", startsWith='" + startsWith + '\'' +
                '}';
    }
}

