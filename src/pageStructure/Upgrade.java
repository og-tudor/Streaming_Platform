package pageStructure;

public class Upgrade extends Page{
    private static Upgrade instance = null;

    public static Upgrade getInstance() {
        if (instance == null) {
            instance = new Upgrade() {
            };
            HomeAuth homeAuth = HomeAuth.getInstance();
            Movies movies = Movies.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();
            instance.insertLink("homeAuth", homeAuth);
            instance.insertLink("movies", movies);
            instance.insertLink("logout", homeUnauth);
        }
        return instance;
    }
}
