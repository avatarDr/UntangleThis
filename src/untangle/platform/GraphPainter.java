package untangle.platform;

import untangle.graph.DrawableGraph;

import java.awt.*;

public interface GraphPainter{
	void paint(Graphics g,DrawableGraph graph,int scale,Point offset);
}