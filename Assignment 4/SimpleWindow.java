import java.util.ArrayList;
import java.util.List;

public class SimpleWindow 
{
	int ID;						// Simple Window's ID
	int width;
	int length;
	int LeftUpperX; 			//The X of uppermost and leftmost of the window
	int LeftUpperY; 			//The Y of uppermost and leftmost of the window
	
	TitleBarTab myTitleBar; 	// Each Simple Window have their own WindowManagerTab.
	
	List<RATWidget> ComponentList;
	
	
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
	 * Only move the window if the new position doesn't pass WindowSystem's width and length.
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
	
	public void AddComponent(RATWidget newWidget)
	{
		newWidget.xPosition += LeftUpperX;
		newWidget.yPosition += LeftUpperY + myTitleBar.length;
		ComponentList.add(newWidget);
		newWidget.parentWindow = this;
	}
	
	public void DetectMouseClick(int x, int y)
	{
		for(RATWidget wdg: ComponentList)
		{
			Class<? extends RATWidget> c = wdg.getClass();
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
						RATButton btn = (RATButton)wdg;
						btn.mouseClicked();						
					}
				}
			}
		}
	}
}
