package pageStructure;

public class HomeUnauth extends Page{
    private static HomeUnauth instance = null;

    public static HomeUnauth getInstance() {
        if (instance == null) {
            instance = new HomeUnauth() {
            };
            Login login = (Login) Login.getInstance();
            Register register = (Register) Register.getInstance();
            links.put("login", login);
            links.put("register", register);
        }
        return instance;
    }
//    public HomeUnauth() {
//        this.name = "homeUnauth";
////        HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();
//
//    }
}
