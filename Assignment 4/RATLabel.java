import java.awt.Color;

public class RATLabel extends RATWidget
{
	Color FontColor;		// Font's color.
	String TextLabel;		// The text this label will display.
	
	public RATLabel(int inputX, int inputY, int inputWidth, int inputLength, Color backColor) {
		super(inputX, inputY, inputWidth, inputLength);
		
		BackgroundColor = backColor;
	}

	/*
	 * Set the text and font color.
	 */
	public void SetText(String newText, Color newFontColor)
	{
		TextLabel = newText;
		FontColor = newFontColor;
	}

	/*
	 * Re-adjust the position of label when the simple window is moved.
	 */
	@Override
	void windowMoved(int deltaX, int deltaY) {
		xPosition -= deltaX;
		yPosition -= deltaY;
		
	}
}
