package pagestructure;

public class Register extends Page {
    private static Register instance = null;
    /** Populates the Details page links and returns the instance */
    public static Register getInstance() {
        if (instance == null) {
            instance = new Register() {
            };
            HomeAuth homeAuth = (HomeAuth) HomeAuth.getInstance();
            HomeUnauth homeUnauth = HomeUnauth.getInstance();

            instance.links.put("homeAuth", homeAuth);
            instance.insertLink("homeUnauth", homeUnauth);
        }
        return instance;
    }


//    public Register() {
//        this.name = "register";
//
//    }
}
