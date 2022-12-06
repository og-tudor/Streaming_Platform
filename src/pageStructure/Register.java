package pageStructure;

public class Register extends Page{
    private static Register instance = null;

    public static Register getInstance() {
        if (instance == null) {
            instance = new Register() {
            };
            HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();
            links.put("homeAuth", homeAuth);
        }
        return instance;
    }
//    public Register() {
//        this.name = "register";
//
//    }
}
