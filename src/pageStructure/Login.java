package pageStructure;

public class Login extends Page {
    private static Login instance = null;

    public static Login getInstance() {
        if (instance == null) {
            instance = new Login() {
            };
//            this.name = "login";
            HomeUnauth homeUnauth = HomeUnauth.getInstance();
            HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();

            instance.insertLink("homeUnauth", homeUnauth);
            instance.insertLink("homeAuth", homeAuth);
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
