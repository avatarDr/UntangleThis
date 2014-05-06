package untangle.graph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DrawableGraph{
	//	0	commonEdges
	//	1	commonVertices
	//	2	highlightedEdges
	//	3	highlightedVertices
	//	4	pickedVertices
	//	5	ownVertices
	private final Set<DrawableEdge>layer0=new HashSet<>();
	private final Set<DrawableVertex>layer1=new HashSet<>();
	private final Set<DrawableEdge>layer2=new HashSet<>();
	private final Set<DrawableVertex>layer3=new HashSet<>();
	private final Set<DrawableVertex>layer4=new HashSet<>();
	private final Set<DrawableVertex>layer5=new HashSet<>();
	
	DrawableGraph(Collection<Edge2D>edges,Collection<Vertex2D>vertices){
		layer0.addAll(edges);
		layer1.addAll(vertices);
	}
	
	private Set<DrawableEdge>getEdgeSet(int i){
		switch(i){
			case 0:return layer0;
			case 2:return layer2;
		}
		return null;
	}
	private Set<DrawableVertex>getVertexSet(int i){
		switch(i){
			case 1:return layer1;
			case 3:return layer3;
			case 4:return layer4;
			case 5:return layer5;
		}
		return null;
	}
	void checkLayer(Drawable d,int oldLayer){
		int newLayer=d.getLayer();
		if(oldLayer==newLayer)return;
		if(d instanceof DrawableVertex){
			getVertexSet(oldLayer).remove(d);
			getVertexSet(newLayer).add((DrawableVertex)d);
		}
		else{
			getEdgeSet(oldLayer).remove(d);
			getEdgeSet(newLayer).add((DrawableEdge)d);
		}
	}
	
	public Set<DrawableEdge>getLayer0(){return layer0;}
	public Set<DrawableVertex>getLayer1(){return layer1;}
	public Set<DrawableEdge>getLayer2(){return layer2;}
	public Set<DrawableVertex>getLayer3(){return layer3;}
	public Set<DrawableVertex>getLayer4(){return layer4;}
	public Set<DrawableVertex>getLayer5(){return layer5;}
}