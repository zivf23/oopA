// TODO: Implement Composite (change this file).

public class Island extends Element {
    public Island(String name, double diameter, String path) {
        //TODO: fix
        super(0, 0, null);
    }

    /**
     * @param visitor
     */
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        //TODO: fix
        return null;
    }

    @Override
    public Habitat getHabitat() {
        return Habitat.AQUATIC;
    }
}
