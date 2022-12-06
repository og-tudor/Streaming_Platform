package pageStructure;

public class Login extends Page {
    private static Login instance = null;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login() {
            };
//            this.name = "login";
            HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();
            links.put("homeAuth", homeAuth);
        }

        return instance;
    }
//    public Login() {
//
//    }
//
//    void login() {
//    }
}
