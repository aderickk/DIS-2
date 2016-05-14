import java.awt.Color;

public class RATButton extends RATLabel
{
	String ActionCommand;
	private RATMouseListener mouseListener;
	
	public RATButton(int inputX, int inputY, int inputWidth, int inputLength, Color backColor) {
		super(inputX, inputY, inputWidth, inputLength, backColor);
		
	}

	public void AssignListener(RATMouseListener newListener) 
	{
		mouseListener = newListener;
	}
	
	public void mouseClicked()
	{
		//System.out.print("Hitt!!!");
		if (mouseListener != null)
			{
				this.mouseListener.mouseClicked(this);
			}
	}

}
