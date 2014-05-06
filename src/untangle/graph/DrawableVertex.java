package untangle.graph;

public interface DrawableVertex extends Drawable{
	float getHighlighted();
	boolean isColliding();
	float x();
	float y();
}