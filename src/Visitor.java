public interface Visitor {
    void visit(Island island);

    void visit(Lake lake);

    void visit(Boat boat);

    void visit(Flag flag);

    void visit(Kite kite);

    void visit(Tree tree);

    void visit(Kid kid);
}