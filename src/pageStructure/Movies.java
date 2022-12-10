package pageStructure;

public class Movies extends Page{
    private static Movies instance = null;

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
