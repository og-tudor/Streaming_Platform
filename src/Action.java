public class Action {
//          "type": "on page",
//          "page": "login",
//          "feature": "login",
//          "credentials": {
//        "name": "Eduard",
//        "password": "secret"
    private String type;
    private String page;
    private String feature;
    private User credentials;

//    public Action(String type, String page, String feature) {
//        this.type = type;
//        this.page = page;
//        this.feature = feature;
//    }

    public Action() {
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

    public User getCredentials() {
        return credentials;
    }

    public void setCredentials(User credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Action{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
