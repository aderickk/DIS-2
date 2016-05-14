import java.awt.Color;

public abstract class RATWidget {
	
	String ID;
	int xPosition;
	int yPosition;
	int width;
	int length;
	Color BackgroundColor;
	SimpleWindow parentWindow;
	
	public RATWidget(int xPosition, int yPosition, int width, int length)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.length = length;
	}
	
	abstract void windowMoved(int deltaX, int deltaY);
	
}
