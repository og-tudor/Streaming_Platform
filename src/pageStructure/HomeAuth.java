package pageStructure;
import java.util.Map;

public class HomeAuth extends Page{
    private static HomeAuth instance = null;

    public static HomeAuth getInstance() {
        if (instance == null) {
            instance = new HomeAuth() {
            };
            Movies movies = (Movies) Movies.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();
            Upgrade upgrade = Upgrade.getInstance();
            instance.insertLink("movies", movies);
            instance.insertLink("logout", homeUnauth);
            instance.insertLink("upgrade", upgrade);
        }
        return instance;
    }

}
