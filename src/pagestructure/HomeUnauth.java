package pagestructure;

public class HomeUnauth extends Page {
    private static HomeUnauth instance = null;
    /** Populates the Home Unauthenticated page links and returns the instance */
    public static HomeUnauth getInstance() {
        if (instance == null) {
            instance = new HomeUnauth() {
            };
//            previousPages = PreviousPages.getInstance();
            Login login = (Login) Login.getInstance();
            Register register = (Register) Register.getInstance();
            instance.insertLink("login", login);
            instance.insertLink("register", register);
        }
        return instance;
    }
//    public HomeUnauth() {
//        this.name = "homeUnauth";
////        HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();
//
//    }
}
