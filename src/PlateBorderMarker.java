import java.util.*;

import processing.core.*;

import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;

public class PlateBorderMarker extends SimpleLinesMarker {
	private static Color color = new Color(80);
	
	public PlateBorderMarker(ShapeFeature f) {
		super(f.getLocations());
	}
	
	public static int getStrokeColor(PGraphics pg) {
		return color.pgColor(pg);
	}

	@Override
	public void draw(PGraphics pg, List<MapPosition> mapPositions) {
		this.setColor(color.pgColor(pg));
		super.draw(pg, mapPositions);
	}
}
