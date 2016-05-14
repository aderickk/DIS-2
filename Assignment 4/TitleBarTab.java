/*
 * Window Manager Tab is the tab where any operation towards window could happen.
 * This class stores the position of Window Manager Tab, as well as any other option
 * the window could have.
 * Window Manager Tab is drawn as part of the Simple Window. 
 */
public class TitleBarTab
{
	int LeftUpperX; //The X of uppermost and leftmost of the window
	int LeftUpperY; //The Y of uppermost and leftmost of the window
	int width;
	int length;
	int CloseOptionX; //The X of uppermost and leftmost of "Close Option".
	int CloseOptionY; //The Y of uppermost and leftmost of "Close Option".
	String WindowName;
	
	/*
	 * Constructor. Get the X, Y, width, and length from Simple Window.  
	 */
	TitleBarTab(int Xori, int Yori, int inputWidth, int inputLength)
	{
		LeftUpperX = Xori;
		LeftUpperY = Yori;
		width = inputWidth;
		length = 25; // The length of Window Manager is always 25.
		// The width of Close Option is 25, and located at the end of Window Manager Tab's width.
		CloseOptionX = (Xori + width) - 25; 
		CloseOptionY = Yori;
	}
	
	/*
	 * Set the window's name
	 */
	public void SetWindowName(String newWindowName)
	{
		WindowName = newWindowName;
	}
	
	/*
	 * The function to Move window position. 
	 * Input parameter is the difference between original position and new position.
	 */
	public void MoveWindow(int deltaX, int deltaY)
	{
		LeftUpperX -= deltaX;
		LeftUpperY -= deltaY;
		CloseOptionX = (LeftUpperX + width) - 25;
		CloseOptionY = LeftUpperY;
	}
}
