package pageStructure;

public class Logout extends Page{
    private static Logout instance = null;

    public static Logout getInstance() {
        if (instance == null) {
            instance = new Logout() {
            };
            HomeUnauth homeUnauth = (HomeUnauth) HomeUnauth.getInstance();
            links.put("homeUnauth", homeUnauth);
        }
        return instance;
    }
//    public Logout() {
//
//    }
}
