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
    protected Map<String, Page> links = new HashMap<>();

    public Map<String, Page> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Page> links) {
        this.links = links;
    }

    public void insertLink(String pageName, Page page) {
        links.put(pageName, page);
    }
    public void printLinks() {
        System.out.println(links);
    }


}
