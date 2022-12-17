package input;

public class Filter {
    private Sort sort;
    private Contains contains;

    public Filter() {
    }
    /** Getter */
    public Sort getSort() {
        return sort;
    }
    /** Setter */
    public void setSort(final Sort sort) {
        this.sort = sort;
    }
    /** Getter */
    public Contains getContains() {
        return contains;
    }
    /** Setter */
    public void setContains(final Contains contains) {
        this.contains = contains;
    }
}
