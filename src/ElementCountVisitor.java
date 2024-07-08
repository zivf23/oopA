public class ElementCountVisitor implements Visitor {
    private int count = 0;

    @Override
    public void visit(Island island) {
        count++;
    }

    @Override
    public void visit(Lake lake) {
        count++;
    }

    @Override
    public void visit(Boat boat) {
        count++;
    }

    @Override
    public void visit(Flag flag) {
        count++;
    }

    @Override
    public void visit(Kite kite) {
        count++;
    }

    @Override
    public void visit(Tree tree) {
        count++;
    }

    @Override
    public void visit(Kid kid) {
        count++;
    }


    // Other visit methods

    public int getCount() {
        return count;
    }
}
