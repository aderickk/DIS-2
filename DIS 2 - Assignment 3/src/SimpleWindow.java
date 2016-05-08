import java.awt.*;

public class SimpleWindow 
{
	int ID;						// Simple Window's ID
	int width;
	int length;
	int LeftUpperX; 			//The X of uppermost and leftmost of the window
	int LeftUpperY; 			//The Y of uppermost and leftmost of the window
	Color MyColor;  			//Requested Color of a window.
	
	WindowManagerTab myWMTab; 	// Each Simple Window have their own WindowManagerTab.
	int LeftUpperXMax;			// The maximum of SimpleWindow's X position.
	int LeftUpperYMax;			// The maximum of SimpleWindow's Y position.
	
	/*
	 * Constructor. Will receive relative X and Y position of Window System, width, length, and color.
	 */
	public SimpleWindow(int Xori, int Yori, int inputWidth, int inputLength, Color inputColor)
	{
		LeftUpperX = Xori;
		LeftUpperY = Yori;
		width = inputWidth;
		length = inputLength;
		MyColor = inputColor;
		myWMTab = new WindowManagerTab(Xori, Yori, width, length);
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
		myWMTab.SetWindowName(newWindowName);
	}
	
	/*
	 * The function to Move window position. 
	 * Input parameter is the difference between original position and new position.
	 * Only move the window if the new position doesn't pass WindowSystem's width and length.
	 */
	public void MoveWindow(int deltaX, int deltaY)
	{
		int newX = LeftUpperX - deltaX;
		int newY = LeftUpperY - deltaY;
		if (newX <= LeftUpperXMax && newX >= 2 && newY <= LeftUpperYMax && newY >= 2)
		{
			LeftUpperX -= deltaX;
			LeftUpperY -= deltaY;
			myWMTab.MoveWindow(deltaX, deltaY);	
		}
	}
	
	/*
	 * Limit the movement of Simple Window. Only within WindowSystem's Width and length.
	 */
	public void SetMaximumPosition(int desktopWidth, int desktopLength)
	{
		LeftUpperXMax = (desktopWidth - width) - 2;
		LeftUpperYMax = (desktopLength - length) - 2;
	}
}
