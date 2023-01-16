package pagestructure;

import java.util.Stack;

public class PreviousPages {
    private static PreviousPages instance = null;
    /** Returns the instance of Singleton */
    public static PreviousPages getInstance() {
        if (instance == null) {
            instance = new PreviousPages() {
            };
        }
        return instance;
    }
    protected Stack<Page> stack = new Stack<Page>();
    /** Getter */
    public Stack<Page> getStack() {
        return stack;
    }
    /** Setter */
    public void setStack(final Stack<Page> stack) {
        this.stack = stack;
    }
    /** ToString */
    @Override
    public String toString() {
        return "PreviousPages{"
                +
                "stack=" + stack
                +
                '}';
    }
}
