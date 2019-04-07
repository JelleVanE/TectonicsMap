import java.util.*;

import processing.core.*;

import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;

public class OrogenMarker extends SimplePolygonMarker {
	private static Color fillColor = new Color(180, 100);
	private static Color strokeColor = new Color(80, 100);

	public OrogenMarker(ShapeFeature f) {
		super(f.getLocations());
	}

	public static int getFillColor(PGraphics pg) {
		return fillColor.pgColor(pg);
	}

	public static int getStrokeColor(PGraphics pg) {
		return strokeColor.pgColor(pg);
	}

	@Override
	public void draw(PGraphics pg, List<MapPosition> mapPositions) {
		this.setColor(fillColor.pgColor(pg));
		this.setStrokeColor(strokeColor.pgColor(pg));
		super.draw(pg, mapPositions);
	}
}
