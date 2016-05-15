import java.awt.Color;

public abstract class RATWidget {
	
	String ID;						
	int xPosition;					// real x-pixel of the widget.
	int yPosition;					// real y-pixel of the widget.
	int width;		
	int length;
	Color BackgroundColor;
	SimpleWindow parentWindow;		// simple window where the widget located.
	
	public RATWidget(int xPosition, int yPosition, int width, int length)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.width = width;
		this.length = length;
	}
	
	/*
	 * Re-adjust the position whenever the window is moved.
	 */
	abstract void windowMoved(int deltaX, int deltaY);
	
}
