import java.awt.Point;

/*
 * Window Manager is an extension of Window System.
 * All similar function with Window System, but with more functionality.
 */
public class WindowManager extends WindowSystem
{
	boolean IsClickWindowManager;		// Flag to determine whether the user click a window manager tab or not.
	SimpleWindow ControlledWindow;		// Variable that stores a window that is currently controlled by user.
	Point OriginalCoordinate;			// Starting point of controlled window. used when moving a Simple Window.
	
	public WindowManager(int i, int j) {
		super(i, j);
		EnableWindowManager = true;		// Enable Window Manager
		ControlledWindow = null;		
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
			super.requestRepaint();
			
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
			super.requestRepaint();
			
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
		if (EnableWindowManager == true)
		{
			// Start the loop from the last one to the beginning.
			for(int currKey = SWMap.lastKey(); currKey >= SWMap.firstKey(); currKey -= 1)
			{
				if (SWMap.containsKey(currKey))
				{
					// Check each Simple Window's WindowManagerTab position. 
					WindowManagerTab targetWM = SWMap.get(currKey).myWMTab;
					int xMin = targetWM.LeftUpperX;
					int xMax = targetWM.LeftUpperX + targetWM.width;
					int yMin = targetWM.LeftUpperY;
					int yMax = targetWM.LeftUpperY + targetWM.length;
					
					if (x >= xMin && x <= xMax)
					{
						if (y>= yMin && y <= yMax)
						{
							moveSelectedWindowToFront(currKey);		// Move the selected SimpleWindow to the front
							IsClickWindowManager = true;			// Set flag to true.
							return;									// Out of the loop.
						}
					}
				}
			}			
		}		
		IsClickWindowManager = false;								// Otherwise, set flag to false.
	}
	
	/*
	 * Move the clicked window to the front.
	 * Do this by deleting window from the TreeMap, and then add it again with new, bigger key.
	 */
	private void moveSelectedWindowToFront(int currKey)
	{
		// Check the last entry of TreeMap 
		if (currKey != SWMap.lastKey())
		{
			// If the last entry is not the selected window, move the selected window to the last.
			
			// Add the same window to the last of the entry, then delete original position of selected window. 
			SimpleWindow temp = SWMap.get(currKey);
			temp.ID = SWMap.lastKey() + 1;				// Assign it with bigger key to ensure last entry position.
			SWMap.put(temp.ID, temp);
			SWMap.remove(currKey);
			super.requestRepaint();						// Repaint the desktop.
			ControlledWindow = SWMap.get(temp.ID);		// Set ControlledWindow to selected window's new ID.
		}
		else
		{
			ControlledWindow = SWMap.get(currKey);		// Set ControlledWindow to selected window.
		}
	}

	private void handleCloseButtonClick(int x)
	{
		if(IsClickWindowManager == true)
		{
			// If user hit close button, remove that window from TreeMap.
			if (x >= ControlledWindow.myWMTab.CloseOptionX)
			{
				SWMap.remove(ControlledWindow.ID);
				super.requestRepaint();
				ControlledWindow = null;
				IsClickWindowManager = false;
			}
		}
	}
}
