package untangle.platform.pc;

import untangle.graph.DrawableEdge;
import untangle.graph.DrawableGraph;
import untangle.graph.DrawableVertex;
import untangle.platform.GraphPainter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Set;

public class MyGraphPainter implements GraphPainter{
	//	0	commonEdges
	//	1	commonVertices
	//	2	highlightedEdges
	//	3	highlightedVertices
	//	4	pickedVertices
	//	5	ownVertices
	private final Color validEdge=Color.decode("#555555");
	private final Color invalidEdge=Color.decode("#F8F8FF");
	private final Color common0=Color.decode("#F5F5F5");
	private final Color common1=Color.decode("#696969");
	private final Color invalid0=Color.decode("#9370DB");
	private final Color invalid1=Color.decode("#9932CC");
	private final Color hovered0=Color.decode("#00008B");
	private final Color hovered1=Color.decode("#00BFFF");
	private final Color own0=Color.decode("#AFEEEE");
	private final Color own1=Color.decode("#00CED1");
	private Image background=null;
	
	{
		try{background=ImageIO.read(ClassLoader.getSystemResource("Space.jpg"));}
		catch(IOException ex){ex.printStackTrace();}
	}
	private void drawCommonVertices(Graphics g,Set<DrawableVertex>vertices,int scale){
		for(DrawableVertex v:vertices){
			g.setColor(v.isColliding()?invalid0:common0);
			int x=Math.round((v.x()-0.5f)*scale);
			int y=-Math.round((v.y()+0.5f)*scale);
			g.fillOval(x,y,scale,scale);
		}
		float f=0.35f;
		for(DrawableVertex v:vertices){
			g.setColor(v.isColliding()?invalid1:common1);
			int x=Math.round((v.x()-f)*scale);
			int y=-Math.round((v.y()+f)*scale);
			g.drawOval(x,y,(int)(scale*2*f),(int)(scale*2*f));
		}
	}
	private void drawEdges(Graphics g,Set<DrawableEdge>edges,int scale){
		for(DrawableEdge e:edges){
			g.setColor(e.isColliding()?invalidEdge:validEdge);
			int x0=Math.round(e.x0()*scale);
			int y0=-Math.round(e.y0()*scale);
			int x1=Math.round(e.x1()*scale);
			int y1=-Math.round(e.y1()*scale);
			g.drawLine(x0,y0,x1,y1);
		}
	}
	private void drawVertices(Graphics g,Set<DrawableVertex>vertices,int scale,Color primary,Color secondary){
		g.setColor(primary);
		for(DrawableVertex v:vertices){
			int x=Math.round((v.x()-0.5f)*scale);
			int y=-Math.round((v.y()+0.5f)*scale);
			g.fillOval(x,y,scale,scale);
		}
		float f=0.35f;
		g.setColor(secondary);
		for(DrawableVertex v:vertices){
			int x=Math.round((v.x()-f)*scale);
			int y=-Math.round((v.y()+f)*scale);
			g.drawOval(x,y,(int)(scale*2*f),(int)(scale*2*f));
		}
	}
	
	private void drawGrid(Graphics g,int size,int offsetX,int offsetY,Color primaryColour,Color secondaryColour){
		Rectangle r=g.getClipBounds();
		g.setColor(secondaryColour);
		for(int i=offsetX%size;i<r.width;i+=size){
			if(i==offsetX)continue;
			g.drawLine(i,0,i,r.height);
		}
		for(int i=offsetY%size;i<r.height;i+=size){
			if(i==offsetY)continue;
			g.drawLine(0,i,r.width,i);
		}
		g.setColor(primaryColour);
		g.drawLine(offsetX,0,offsetX,r.height);
		g.drawLine(0,offsetY,r.width,offsetY);
	}
	
	@Override
	public void paint(Graphics g,DrawableGraph graph,int scale,Point offset){
		g.drawImage(background,0,0,null);
		g.translate(offset.x,offset.y);
		
		//drawGrid(g,10*scale,offset.x,offset.y,Color.DARK_GRAY,Color.DARK_GRAY);
		
		drawEdges(g,graph.getLayer0(),scale);
		drawCommonVertices(g,graph.getLayer1(),scale);
		drawEdges(g,graph.getLayer2(),scale);
		drawVertices(g,graph.getLayer3(),scale,hovered0,hovered1);
		//TODO missing layer 4;
		drawVertices(g,graph.getLayer5(),scale,own0,own1);
		
		g.translate(-offset.x,-offset.y);
	}
}