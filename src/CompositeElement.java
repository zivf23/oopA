import java.util.ArrayList;
import java.util.List;

public class CompositeElement extends Element {
    private final List<Element> children = new ArrayList<>();
    private final String name;

    public CompositeElement(String name) {
        this.name = name;
    }

    public void add(Element element) {
        // Add element to the children list
        children.add(element);
    }

    public List<Element> getChildren() {
        return children;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element child : children) {
            child.accept(visitor);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public Habitat getHabitat() {
        // Implement this method based on your requirements
        return null; // Placeholder implementation
    }
}
