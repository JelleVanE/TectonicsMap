import processing.core.*;

public class Color {
	private int r, g, b, opacity;
	
	public Color(int r, int g, int b, int opacity) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.opacity = opacity;
	}
	
	public Color(int r, int g, int b) {
		this(r, g, b, 255);
	}
	
	public Color(int white, int opacity) {
		this(white, white, white, opacity);
	}
	
	public Color(int white) {
		this(white, 255);
	}
	
	public int pgColor(PGraphics pg) {
		return pg.color(r, g, b, opacity);
	}
}
