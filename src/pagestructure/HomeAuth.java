package pagestructure;

public class HomeAuth extends Page {
    private static HomeAuth instance = null;
    /** Populates the Home Authenticated page links and returns the instance */
    public static HomeAuth getInstance() {
        if (instance == null) {
            instance = new HomeAuth() {
            };
            Movies movies = (Movies) Movies.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();
            Upgrade upgrade = Upgrade.getInstance();

            instance.insertLink("movies", movies);
            instance.insertLink("logout", homeUnauth);
            instance.insertLink("upgrades", upgrade);
        }
        return instance;
    }

}
