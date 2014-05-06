package untangle.graph;

public interface DrawableEdge extends Drawable{
	boolean isColliding();
	float x0();
	float x1();
	float y0();
	float y1();
}