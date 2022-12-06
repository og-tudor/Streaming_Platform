package pageStructure;

import java.util.HashMap;
import java.util.Map;

public abstract class Page {
    private static Page instance = null;

    public static Page getInstance() {
        if (instance == null) {
            instance = new Page() {
            };
        }
        return instance;
    }
    String name;
    static Map<String, Page> links = new HashMap<>();

    public static Map<String, Page> getLinks() {
        return links;
    }

    public static void setLinks(Map<String, Page> links) {
        Page.links = links;
    }

    void insertLink(String pageName, Page page) {
        links.put(pageName, page);
    }
    void printLinks() {
        System.out.println(links);
    }

}
