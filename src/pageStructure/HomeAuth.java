package pageStructure;

public class HomeAuth extends Page{
    private static HomeAuth instance = null;

    public static HomeAuth getInstance() {
        if (instance == null) {
            instance = new HomeAuth() {
            };
            Movies movies = (Movies) Movies.getInstance();
            Logout logout = (Logout) Logout.getInstance();
            links.put("movies", movies);
            links.put("logout", logout);
        }
        return instance;
    }
//    public HomeAuth() {
//
//    }
}
