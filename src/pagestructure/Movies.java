package pagestructure;

public class Movies extends Page {
    private static Movies instance = null;
    /** Populates the Movies page links and returns the instance */
    public static Movies getInstance() {
        if (instance == null) {
            instance = new Movies() {
            };
            HomeAuth homeAuth = HomeAuth.getInstance();
            Details details = Details.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();

            instance.insertLink("homeAuth", homeAuth);
            instance.insertLink("see details", details);
            instance.insertLink("logout", homeUnauth);
        }
        return instance;
    }
    public Movies() {
    }

}
