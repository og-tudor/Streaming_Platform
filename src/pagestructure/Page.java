package pagestructure;

import java.util.HashMap;
import java.util.Map;

public abstract class Page {
    private static Page instance = null;
    /** Returns the instance of Singleton */
    public static Page getInstance() {
        if (instance == null) {
            instance = new Page() {
            };
        }
        return instance;
    }
    protected Map<String, Page> links = new HashMap<>();
    /** Getter */
    public Map<String, Page> getLinks() {
        return links;
    }
    /** Setter */
    public void setLinks(final Map<String, Page> links) {
        this.links = links;
    }
    /** Inserts a page link into the links array */
    public void insertLink(final String pageName, final Page page) {
        links.put(pageName, page);
    }
    /** Prints all links in a page */
    public void printLinks() {
        System.out.println(links);
    }
}
