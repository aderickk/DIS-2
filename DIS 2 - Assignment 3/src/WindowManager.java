import java.awt.Point;
import java.util.Map;

public class WindowManager extends WindowSystem
{
	boolean IsClickWindowManager;
	SimpleWindow ControlledWindow;
	Point OriginalCoordinate;
	
	public WindowManager(int i, int j) {
		super(i, j);
		EnableWindowManager = true;
		ControlledWindow = null;
	}	
	
	public void handleMouseClicked(int x, int y)
	{
		//System.out.print("\nClicked at X: " + x + " Y: " + y);
		isClickWindowManager(x,y);
		handleCloseButtonClick(x);
	}
	
	public void handleMousePressed(int x, int y)
	{
		//System.out.print("\nPressed at X: " + x + " Y: " + y);
		isClickWindowManager(x,y);
		if (IsClickWindowManager == true)
		{
			OriginalCoordinate = new Point(x,y);
		}
	}
	
	public void handleMouseDragged(int x, int y)
	{
		if (IsClickWindowManager == true)
		{
			int deltaX = OriginalCoordinate.x - x;
			int deltaY = OriginalCoordinate.y - y;
			ControlledWindow.MoveWindow(deltaX, deltaY);
			super.requestRepaint();
			
			OriginalCoordinate = new Point(x,y);
		}		
	}
	
	public void handleMouseReleased(int x,int y)
	{		
		//System.out.print("\nReleased at X: " + x + " Y: " + y);
		if (IsClickWindowManager == true)
		{
			int deltaX = OriginalCoordinate.x - x;
			int deltaY = OriginalCoordinate.y - y;
			ControlledWindow.MoveWindow(deltaX, deltaY);
			super.requestRepaint();
			
			ControlledWindow = null;
		}
		IsClickWindowManager = false;
	}
		
	private void isClickWindowManager(int x, int y)
	{
		if (EnableWindowManager == true)
		{
			for(Map.Entry<String, SimpleWindow> member: SWMap.entrySet())
			{
				WindowManagerTab targetWM = member.getValue().myWMTab;
				int xMin = targetWM.LeftUpperX;
				int xMax = targetWM.LeftUpperX + targetWM.width;
				int yMin = targetWM.LeftUpperY;
				int yMax = targetWM.LeftUpperY + targetWM.length;
				
				if (x >= xMin && x <= xMax)
				{
					if (y>= yMin && y <= yMax)
					{
						ControlledWindow = member.getValue();
						IsClickWindowManager = true;
						return;
					}
				}
			}
		}		
		IsClickWindowManager = false;
	}

	private void handleCloseButtonClick(int x)
	{
		if(IsClickWindowManager == true)
		{
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
