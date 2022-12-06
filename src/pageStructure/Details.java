package pageStructure;

public class Details extends Page{
    private static Details instance = null;

    public static Details getInstance() {
        if (instance == null) {
            instance = new Details() {
            };
            HomeAuth homeAuth = HomeAuth.getInstance();
            Movies movies = Movies.getInstance();
            Upgrade upgrade = Upgrade.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();
            instance.insertLink("homeAuth", homeAuth);
            instance.insertLink("movies", movies);
            instance.insertLink("upgrade", upgrade);
            instance.insertLink("logout", homeUnauth);

        }
        return instance;
    }
}
