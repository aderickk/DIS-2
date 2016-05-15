import java.awt.Color;

public class RATButton extends RATLabel
{
	String ActionCommand;						// Specify the action the button do.
	private RATMouseListener mouseListener;		// The mouse click listener of a button.
	
	public RATButton(int inputX, int inputY, int inputWidth, int inputLength, Color backColor) {
		super(inputX, inputY, inputWidth, inputLength, backColor);
		
	}

	/*
	 * Assign a button's mouse click listener to an event.
	 */
	public void AssignListener(RATMouseListener newListener) 
	{
		mouseListener = newListener;
	}
	
	/*
	 * If a mouse is clicked, do the button's mouse click listener.
	 */
	public void mouseClicked()
	{
		if (mouseListener != null)
			{
				this.mouseListener.mouseClicked(this);
			}
	}

}
