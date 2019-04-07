import processing.core.*;

import java.util.*;

import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;

public class TectonicsMap extends PApplet {
	private static final long serialVersionUID = 1L;

	private UnfoldingMap map;

	private int defaultWidth, defaultHeight;

	private List<Marker> quakeMarkers;
	private List<Marker> plateMarkers;
	private List<Marker> orogenMarkers;

	public int getDefaultWidth() {
		return defaultWidth;
	}

	public int getDefaultHeight() {
		return defaultHeight;
	}

	private void setupMarkers() {
		List<Feature> quakes = GeoJSONReader.loadData(this,
				"https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_month.geojson");
		List<Feature> plates = GeoJSONReader.loadData(this,
				"https://raw.githubusercontent.com/fraxen/tectonicplates/master/GeoJSON/PB2002_boundaries.json");
		List<Feature> orogens = GeoJSONReader.loadData(this,
				"https://raw.githubusercontent.com/fraxen/tectonicplates/master/GeoJSON/PB2002_orogens.json");

		quakeMarkers = new ArrayList<Marker>();
		for (Feature f : quakes) {
			quakeMarkers.add(new QuakeMarker((PointFeature) f, f.getProperties()));
		}

		plateMarkers = new ArrayList<Marker>();
		for (Feature f : plates) {
			plateMarkers.add(new PlateBorderMarker((ShapeFeature) f));
		}

		orogenMarkers = new ArrayList<Marker>();
		for (Feature f : orogens) {
			// The orogens object contains some MultiFeatures that contain multiple
			// ShapeFeatures. We have to split these up.
			if (f instanceof MultiFeature) {
				for (Feature ff : ((MultiFeature) f).getFeatures()) {
					orogenMarkers.add(new OrogenMarker((ShapeFeature) ff));
				}
			} else {
				orogenMarkers.add(new OrogenMarker((ShapeFeature) f));
			}
		}
	}

	public void setup() {
		defaultWidth = 1200;
		defaultHeight = 800;
		size(defaultWidth, defaultHeight);
		map = new UnfoldingMap(this, 0, 0, this.width, this.height, new OpenStreetMap.OpenStreetMapProvider());
		MapUtils.createDefaultEventDispatcher(this, map);
		map.setZoomRange(2.23f, 20f);
		map.zoomToLevel(0);

		setupMarkers();

		map.addMarkers(quakeMarkers);
		map.addMarkers(plateMarkers);
		map.addMarkers(orogenMarkers);
	}
	
	public void drawKey() {
		int distance = 25, width = 215, height = 90, x = distance, y = this.height - height - distance;
		strokeWeight(1.2f);
		fill(255);
		rect(x, y, width, height);
		fill(QuakeMarker.getFillColor(this.g));
		stroke(QuakeMarker.getStrokeColor(this.g));
		ellipse(x+15, y+15, 12.5f, 12.5f);
		fill(0);
		text("Earthquake (size ~ magnitude)", x+30, y+20);
		stroke(PlateBorderMarker.getStrokeColor(this.g));
		line(x+7.5f, y+45f, x+22.5f, y+45f);
		text("Tectonic plate boundary", x+30, y+50f);
		fill(OrogenMarker.getFillColor(this.g));
		stroke(OrogenMarker.getStrokeColor(this.g));
		rect(x+7.5f, y+70, 15, 10);
		stroke(0);
		fill(0);
		text("Orogenic belt", x+30, y+79);
	}

	public void draw() {
		map.draw();
		drawKey();
	}
}
