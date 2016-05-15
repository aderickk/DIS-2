import java.util.*;
import java.util.Map.Entry;

import de.rwth.hci.Graphics.GraphicsEventSystem;
import java.awt.Color;

public class WindowSystem extends GraphicsEventSystem
{
	int width;
	int length;
	Map <String, int[]> Lines;				// Collection of Lines. 
	int LineCount;
	TreeMap<Integer, SimpleWindow> SWMap;	// Collection of SimpleWindow.
	boolean EnableWindowManager;
	WindowManager myWindowManager;			// The window manager of Window System.
	
	public WindowSystem(int i, int j)
	{
		super(i, j);
		width = i;
		length = j;
		Lines = new HashMap<>();
		LineCount = 0;
		SWMap = new TreeMap<Integer, SimpleWindow>();
		super.setBackground(Color.DARK_GRAY);
		EnableWindowManager = false;
				
		myWindowManager = new WindowManager(this);
		if (myWindowManager != null)
		{
			EnableWindowManager = true;
		}
	}

	public void handlePaint()
	{	
		// Draw all SimpleWindow in the TreeMap.
		for(Map.Entry<Integer, SimpleWindow> member: SWMap.entrySet())
		{
			// Draw SimpleWindow's window.
			SimpleWindow newSW = member.getValue();
			int EndX = newSW.LeftUpperX + newSW.width;
			int EndY = newSW.LeftUpperY + newSW.length;  
			super.setColor(Color.LIGHT_GRAY);
			super.fillRect(newSW.LeftUpperX, newSW.LeftUpperY, EndX, EndY);
			
			// Draw all widget in a simple window.
			drawComponent(newSW.ComponentList);
			
			// If enabled, draw Window Manager.
			if (EnableWindowManager == true)
			{
				DrawWindowManager(newSW.myTitleBar);
			}
			
			// Draw SimpleWindow border.
			super.setColor(Color.black);
			super.drawRect(newSW.LeftUpperX, newSW.LeftUpperY, EndX, EndY);
			
		}		
	}	
	
	/*
	 * Draw each SimpleWindow's window manager tab.
	 */
	private void DrawWindowManager(TitleBarTab newTitleBar)
	{
		// Draw the Window Manager. Stacked on the original Simple Window.
		int wmRightBottomX = newTitleBar.LeftUpperX + newTitleBar.width;
		int wmRightBottomY = newTitleBar.LeftUpperY + newTitleBar.length;
		super.setColor(Color.white);
		super.fillRect(newTitleBar.LeftUpperX, newTitleBar.LeftUpperY, wmRightBottomX, wmRightBottomY);
		super.setColor(Color.black);
		super.drawRect(newTitleBar.LeftUpperX, newTitleBar.LeftUpperY, wmRightBottomX, wmRightBottomY);
		
		// Draw Window Manager title.
		super.setColor(Color.black);
		int FontX = newTitleBar.LeftUpperX + 3;
		int FontY = newTitleBar.LeftUpperY + 16; // Draw the title in the middle of Window Manager
		super.drawString(newTitleBar.WindowName, FontX, FontY);		
		
		// Draw close button.
		super.setColor(Color.red);
		super.fillRect(newTitleBar.CloseOptionX, newTitleBar.CloseOptionY, wmRightBottomX, wmRightBottomY);
		super.setColor(Color.black);
		super.drawRect(newTitleBar.CloseOptionX, newTitleBar.CloseOptionY, wmRightBottomX, wmRightBottomY);		
		super.drawLine(newTitleBar.CloseOptionX, newTitleBar.LeftUpperY, wmRightBottomX, wmRightBottomY);
		int CBLeftBottomY = newTitleBar.LeftUpperY + newTitleBar.length;
		super.drawLine(newTitleBar.CloseOptionX, CBLeftBottomY, wmRightBottomX, newTitleBar.LeftUpperY);				
	}
	
	/*
	 * Draw a collection of widget.
	 */
	private void drawComponent(List<RATWidget> componentList)
	{
		for(RATWidget wdg: componentList)
		{
			Class<? extends RATWidget> c = wdg.getClass();
			if (c.equals(RATLabel.class))
			{
				// Draw RATLabel.
				drawLabel((RATLabel) wdg);
			}
			else if (c.equals(RATButton.class))
			{
				// Draw RATButton.
				drawButton((RATButton) wdg);
			}
		}
	}
	
	// Draw RATLabel.
	private void drawLabel(RATLabel label)
	{
		// Draw the Rectangle
		int Xend = label.xPosition + label.width;
		int Yend = label.yPosition + label.length;
		super.setColor(label.BackgroundColor);
		super.fillRect(label.xPosition, label.yPosition, Xend, Yend);
		super.setColor(Color.black);
		super.drawRect(label.xPosition, label.yPosition, Xend, Yend);
		
		// Draw the Text
		super.setColor(label.FontColor);
		int FontX = label.xPosition + 3;
		int FontY = label.yPosition + 15;
		super.drawString(label.TextLabel, FontX, FontY);		
	}
	
	// Draw RATButton.
	private void drawButton(RATButton button)
	{
		// Draw the Rectangle
		int Xend = button.xPosition + button.width;
		int Yend = button.yPosition + button.length;
		super.setColor(button.BackgroundColor);
		super.fillRect(button.xPosition, button.yPosition, Xend, Yend);
		super.setColor(Color.black);
		super.drawRect(button.xPosition, button.yPosition, Xend, Yend);
		
		// Draw the Text
		super.setColor(button.FontColor);
		int FontX = button.xPosition + 3;
		int FontY = button.yPosition + 15;
		super.drawString(button.TextLabel, FontX, FontY);		
	}
	
	/*
	 * Create Simple Window. it receive percentage of X and Y, width, height,  and window name.
	 */
	public void CreateSimpleWindow(float reqX, float reqY, int reqWidth, int reqHeight, String windowName)
	{
		// Calculate the real position of simple window in desktop.
		int realStartX = (int) Math.round(reqX * width);
		int realStartY = (int) Math.round(reqY * length);
		
		// Create the Simple Window. 
		SimpleWindow newSW = new SimpleWindow(realStartX, realStartY, reqWidth, reqHeight);
		
		// give new SimpleWindow ID based on WindowSystem's collection of Simple Window.
		int newKey;
		if (SWMap.size() == 0) 
			{newKey = 1;}
		else
			{newKey = SWMap.lastKey() + 1;}
		newSW.SetID(newKey);
		SWMap.put(newKey, newSW);						// Add SimpleWindow to TreeMap.
		
		if (EnableWindowManager)
		{
			myWindowManager.SWMap.put(newKey, newSW);
		}
		
		newSW.SetWindowName(windowName);				// Set SimpleWindow's Title.
	}
	
	// Return a simpleWindow based on the window name.
	public SimpleWindow GetSimpleWindow(String windowName)
	{
		for(Entry<Integer, SimpleWindow> entry:SWMap.entrySet())
		{
			SimpleWindow sw = entry.getValue();
			if (sw.myTitleBar.WindowName == windowName)
			{
				return sw;
			}
		}
		return null;
	}
	
	// Check whether a mouse click happen inside a simpleWindow or not.
	private void checkMouseClick(int x, int y)
	{
		for(int currKey = SWMap.lastKey(); currKey >= SWMap.firstKey(); currKey -= 1)
		{
			if (SWMap.containsKey(currKey))
			{
				// Check each Simple Window's WindowManagerTab position. 
				SimpleWindow targetWindow = SWMap.get(currKey);
				int xMin = targetWindow.LeftUpperX;
				int xMax = targetWindow.LeftUpperX + targetWindow.width;
				int yMin = targetWindow.LeftUpperY;
				int yMax = targetWindow.LeftUpperY + targetWindow.length;
							
				if (x >= xMin && x <= xMax)
				{
					if (y>= yMin && y <= yMax)
					{
						// If it is inside a simpleWindow, let simpleWindow handle the click.
						targetWindow.DetectMouseClick(x, y);
						return;									// Out of the loop.
					}
				}
			}
		}			
						
		//IsClickWindowManager = false;
	}
	
	@Override
	public void handleMouseClicked(int x, int y)
	{
		checkMouseClick(x,y);		
		if (EnableWindowManager)
		{
			myWindowManager.handleMouseClicked(x, y);
		}		
	}
	
	@Override
	public void handleMousePressed(int x, int y)
	{
		if(EnableWindowManager)
		{
			myWindowManager.handleMousePressed(x, y);
		}
	}
	
	@Override
	public void handleMouseDragged(int x, int y)
	{
		if (EnableWindowManager)
		{
			myWindowManager.handleMouseDragged(x, y);
		}
	}
	
	@Override
	public void handleMouseReleased(int x, int y)
	{
		if (EnableWindowManager)
		{
			myWindowManager.handleMouseReleased(x, y);
		}
	}
	
}
