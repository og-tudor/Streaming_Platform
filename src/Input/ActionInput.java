package Input;

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
    private CredentialsInput credentials;

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

    public CredentialsInput getCredentials() {
        return credentials;
    }

    public void setCredentials(CredentialsInput credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Input.Action{" +
                "type='" + type + '\'' +
                ", page='" + page + '\'' +
                ", feature='" + feature + '\'' +
                ", credentials=" + credentials +
                '}';
    }
}
