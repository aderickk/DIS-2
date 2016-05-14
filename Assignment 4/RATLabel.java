import java.awt.Color;

public class RATLabel extends RATWidget
{
	Color FontColor;
	String TextLabel;
	
	public RATLabel(int inputX, int inputY, int inputWidth, int inputLength, Color backColor) {
		super(inputX, inputY, inputWidth, inputLength);
		
		BackgroundColor = backColor;
	}

	public void SetText(String newText, Color newFontColor)
	{
		TextLabel = newText;
		FontColor = newFontColor;
	}

	@Override
	void windowMoved(int deltaX, int deltaY) {
		xPosition -= deltaX;
		yPosition -= deltaY;
		
	}
}
