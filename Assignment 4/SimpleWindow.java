import java.util.ArrayList;
import java.util.List;

public class SimpleWindow 
{
	int ID;							// Simple Window's ID
	int width;
	int length;
	int LeftUpperX; 				//The X of uppermost and leftmost of the window
	int LeftUpperY;		 			//The Y of uppermost and leftmost of the window
	
	TitleBarTab myTitleBar; 		// Each Simple Window have their own WindowManagerTab.
	
	List<RATWidget> ComponentList;	// List of all widget a simple window contain. 
	
	
	/*
	 * Constructor. Will receive relative X and Y position of Window System, width, length, and color.
	 */
	public SimpleWindow(int Xori, int Yori, int inputWidth, int inputLength)
	{
		LeftUpperX = Xori;
		LeftUpperY = Yori;
		width = inputWidth;
		length = inputLength;
		myTitleBar = new TitleBarTab(Xori, Yori, width, length);
		ComponentList = new ArrayList<RATWidget>();
	}		
	
	/*
	 *  Set Simple Window's ID.
	 */
	public void SetID(int newID)
	{
		if (ID == 0)
		{
			ID = newID;
		}
	}
	
	/*
	 *  Set Simple Window's Title.
	 */
	public void SetWindowName(String newWindowName)
	{
		myTitleBar.SetWindowName(newWindowName);
	}
	
	/*
	 * The function to Move window position. 
	 * Input parameter is the difference between original position and new position.
	 * Move all the widget the simple window has as well.
	 */
	public void MoveWindow(int deltaX, int deltaY)
	{
		LeftUpperX -= deltaX;
		LeftUpperY -= deltaY;
		myTitleBar.MoveWindow(deltaX, deltaY);	
		for(RATWidget wdg: ComponentList)
		{
			wdg.windowMoved(deltaX, deltaY);
		}
	}	
	
	/*
	 * Assign a widget to simple window.
	 */
	public void AddComponent(RATWidget newWidget)
	{
		newWidget.xPosition += LeftUpperX;
		newWidget.yPosition += LeftUpperY + myTitleBar.length;
		ComponentList.add(newWidget);
		newWidget.parentWindow = this;
	}
	
	/*
	 * Determine if the click happen inside a button or not.
	 */
	public void DetectMouseClick(int x, int y)
	{
		for(RATWidget wdg: ComponentList)
		{
			Class<? extends RATWidget> c = wdg.getClass();
			
			// only do the check if the widget is a button.
			if (c.equals(RATButton.class))
			{
				int xBegin = wdg.xPosition;
				int xEnd = wdg.xPosition + wdg.width;
				int yBegin = wdg.yPosition;
				int yEnd = wdg.yPosition + wdg.length;
	
				if(x >= xBegin && x <= xEnd)
				{
					if(y >= yBegin && y <= yEnd)
					{
						// If it is a button, then do the button's mouseClicked, 
						// which in turn will activate the mouse click listener.
						RATButton btn = (RATButton)wdg;
						btn.mouseClicked();						
					}
				}
			}
		}
	}
}
