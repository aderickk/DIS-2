import java.awt.Point;
import java.util.TreeMap;

/*
 * Window Manager is an extension of Window System.
 * All similar function with Window System, but with more functionality.
 */
public class WindowManager
{
	WindowSystem myWindowSystem;
	boolean IsClickWindowManager;		// Flag to determine whether the user click a window manager tab or not.
	SimpleWindow ControlledWindow;		// Variable that stores a window that is currently controlled by user.
	Point OriginalCoordinate;			// Starting point of controlled window. used when moving a Simple Window.
	TreeMap<Integer, SimpleWindow> SWMap;
	
	public WindowManager(WindowSystem windowSystem) {
		ControlledWindow = null;
		myWindowSystem = windowSystem;
		SWMap = new TreeMap<Integer, SimpleWindow>();
	}	
	
	public void handleMouseClicked(int x, int y)
	{
		isClickWindowManager(x,y);				// Determine whether user click on a Window Manager tab or not.
		handleCloseButtonClick(x);				// Determine whether user click on close button option or not.
	}
	
	public void handleMousePressed(int x, int y)
	{
		isClickWindowManager(x,y);				// Determine whether user click on a Window Manager tab or not.
		if (IsClickWindowManager == true)
		{
			OriginalCoordinate = new Point(x,y); // Set the Original Coordinate to the point where mouse is pressed.
		}
	}
	
	public void handleMouseDragged(int x, int y)
	{
		if (IsClickWindowManager == true)
		{
			// count the difference between Original Coordinate and current Coordinate.
			int deltaX = OriginalCoordinate.x - x;			
			int deltaY = OriginalCoordinate.y - y;
			
			// Move the controlled window, then redraw.
			ControlledWindow.MoveWindow(deltaX, deltaY);
			//super.requestRepaint();
			myWindowSystem.requestRepaint();
			
			OriginalCoordinate = new Point(x,y);  // set the current point as "Original Coordinate".
		}		
	}
	
	public void handleMouseReleased(int x,int y)
	{		
		if (IsClickWindowManager == true)
		{
			// count the difference between Original Coordinate and current Coordinate.
			int deltaX = OriginalCoordinate.x - x;
			int deltaY = OriginalCoordinate.y - y;
			
			// Move the controlled window, then redraw.
			ControlledWindow.MoveWindow(deltaX, deltaY);
			//super.requestRepaint();
			myWindowSystem.requestRepaint();
			
			ControlledWindow = null;			// release Controlled Window
		}
		IsClickWindowManager = false;			// set the flag to false.
	}
		
	/*
	 * Determine whether user click on Window Manager Tab or not.
	 * Loop through the TreeMap's XY position and determine which Simple Window is under control.
	 * Start the loop from the last entry of TreeMap, since the last entry is the one on the most top.
	 */
	private void isClickWindowManager(int x, int y)
	{		
		// Start the loop from the last one to the beginning.
		for(int currKey = SWMap.lastKey(); currKey >= SWMap.firstKey(); currKey -= 1)
		{
			if (SWMap.containsKey(currKey))
			{
				// Check each Simple Window's WindowManagerTab position. 
				TitleBarTab targetWM = SWMap.get(currKey).myTitleBar;
				int xMin = targetWM.LeftUpperX;
				int xMax = targetWM.LeftUpperX + targetWM.width;
				int yMin = targetWM.LeftUpperY;
				int yMax = targetWM.LeftUpperY + targetWM.length;
					
				if (x >= xMin && x <= xMax)
				{
					if (y>= yMin && y <= yMax)
					{
						ControlledWindow = SWMap.get(currKey);
						IsClickWindowManager = true;			// Set flag to true.
						return;									// Out of the loop.
					}
				}
			}
		}			
				
		IsClickWindowManager = false;								// Otherwise, set flag to false.
	}
	
	private void handleCloseButtonClick(int x)
	{
		// If user hit close button, remove that window from TreeMap.
		if (IsClickWindowManager && x >= ControlledWindow.myTitleBar.CloseOptionX)
		{
			SWMap.remove(ControlledWindow.ID);
			myWindowSystem.SWMap.remove(ControlledWindow.ID);
			myWindowSystem.requestRepaint();
			ControlledWindow = null;
			IsClickWindowManager = false;
		}		
	}
}
