package pageStructure;

public class Movies extends Page{
    private static Movies instance = null;

    public static Movies getInstance() {
        if (instance == null) {
            instance = new Movies() {
            };
        }
        return instance;
    }
    public Movies() {
    }

    @Override
    void insertLink(String pageName, Page page) {
        super.insertLink(pageName, page);
    }

    @Override
    void printLinks() {
        super.printLinks();
    }
}
