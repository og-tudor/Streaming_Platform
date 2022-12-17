package pageStructure;

public class CurrentPage extends Page {
    private static Page instance = null;

    public static Page getInstance() {
        if (instance == null) {
            instance = (Page) HomeUnauth.getInstance(); {
            };
        }
        return (Page) instance;
    }

    public static void changePage(Page newPage) {
//        if (HomeUnauth.getInstance().equals(newPage)) {
//            instance = HomeUnauth.getInstance();
//        }
//        if (HomeAuth.getInstance().equals(newPage)) {
//            instance = HomeAuth.getInstance();
//        }
//        if (HomeUnauth.getInstance().equals(newPage)) {
//            instance = HomeUnauth.getInstance();
//        }
//        if (HomeUnauth.getInstance().equals(newPage)) {
//            instance = HomeUnauth.getInstance();
//        }
        instance = newPage;
    }
}
