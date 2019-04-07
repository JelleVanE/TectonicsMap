import processing.core.*;

import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;

public class QuakeMarker extends SimplePointMarker {
	private static Color fillColor = new Color(255, 120, 40);
	private static Color strokeColor = new Color(255, 80, 40);
	
	public QuakeMarker(PointFeature f, java.util.HashMap<java.lang.String,java.lang.Object> properties) {
		super(f.getLocation(), properties);
	}
	
	public float getMagnitude() {
		return Float.parseFloat(this.getProperty("mag").toString());
	}
	
	public static int getFillColor(PGraphics pg) {
		return fillColor.pgColor(pg);
	}
	
	public static int getStrokeColor(PGraphics pg) {
		return strokeColor.pgColor(pg);
	}
	
	@Override
	public void draw(PGraphics pg, float x, float y) {
		this.setColor(fillColor.pgColor(pg));
		this.setStrokeColor(strokeColor.pgColor(pg));
		this.setRadius(1.5f*getMagnitude()); 
		super.draw(pg, x, y);
	}
}