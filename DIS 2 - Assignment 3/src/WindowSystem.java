import java.io.*;
import java.util.*;
import de.rwth.hci.Graphics.GraphicsEventSystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

public class WindowSystem extends GraphicsEventSystem
{
	int width;
	int length;
	Map <String, int[]> Lines; 
	int LineCount;
	Map <String, SimpleWindow> SWMap;
	boolean EnableWindowManager;
	
	public WindowSystem(int i, int j)
	{
		super(i, j);
		width = i;
		length = j;
		Lines = new HashMap<>();
		LineCount = 0;
		SWMap = new HashMap<>();
		super.setBackground(Color.LIGHT_GRAY);
		EnableWindowManager = false;
	}

	public void drawLine(float StartX, float StartY, float EndX, float EndY)
	{		
		int realStartX = (int) Math.round(StartX * width);
		int realEndX = (int) Math.round(EndX * width);
		int realStartY = (int) Math.round(StartY * length);
		int realEndY = (int) Math.round(EndY * length);
		LineCount++;
		Lines.put(Integer.toString(LineCount), new int[]{realStartX, realStartY, realEndX, realEndY});
	}

	public void handlePaint()
	{	
		// Draw all lines in the collection.
		super.setColor(Color.black);
		for(Map.Entry<String, int[]> lineMember: Lines.entrySet())
		{
			super.drawLine(lineMember.getValue()[0], lineMember.getValue()[1], lineMember.getValue()[2], lineMember.getValue()[3]);
		}
			
		// Draw all SimpleWindow in the list.
		for(Map.Entry<String, SimpleWindow> member: SWMap.entrySet())
		{
			// Draw SimpleWindow's window.
			SimpleWindow newSW = member.getValue();
			int EndX = newSW.LeftUpperX + newSW.width;
			int EndY = newSW.LeftUpperY + newSW.length;  
			super.setColor(newSW.MyColor);
			super.fillRect(newSW.LeftUpperX, newSW.LeftUpperY, EndX, EndY);
			
			if (EnableWindowManager == true)
			{
				DrawWindowManager(newSW.myWMTab, newSW.MyColor);
			}
			// Draw SimpleWindow border.
			super.setColor(Color.black);
			super.drawRect(newSW.LeftUpperX, newSW.LeftUpperY, EndX, EndY);
			
		}		
	}	
	
	private void DrawWindowManager(WindowManagerTab newWM, Color newColor)
	{
		// Draw the Window Manager. Stacked on the original Simple Window.
		int wmRightBottomX = newWM.LeftUpperX + newWM.width;
		int wmRightBottomY = newWM.LeftUpperY + newWM.length;
		super.setColor(Color.white);
		super.fillRect(newWM.LeftUpperX, newWM.LeftUpperY, wmRightBottomX, wmRightBottomY);
		super.setColor(Color.black);
		super.drawRect(newWM.LeftUpperX, newWM.LeftUpperY, wmRightBottomX, wmRightBottomY);
		
		// Draw Window Manager title.
		super.setColor(Color.black);
		int FontX = newWM.LeftUpperX + 3;
		int FontY = newWM.LeftUpperY + 16; // Draw the title in the middle of Window Manager
		super.drawString(newWM.WindowName, FontX, FontY);		
		
		// Draw close button.
		super.setColor(Color.red);
		super.fillRect(newWM.CloseOptionX, newWM.CloseOptionY, wmRightBottomX, wmRightBottomY);
		super.setColor(Color.black);
		super.drawRect(newWM.CloseOptionX, newWM.CloseOptionY, wmRightBottomX, wmRightBottomY);		
		super.drawLine(newWM.CloseOptionX, newWM.LeftUpperY, wmRightBottomX, wmRightBottomY);
		int CBLeftBottomY = newWM.LeftUpperY + newWM.length;
		super.drawLine(newWM.CloseOptionX, CBLeftBottomY, wmRightBottomX, newWM.LeftUpperY);				
	}
	
	public void CreateSimpleWindow(float reqX, float reqY, int reqWidth, int reqHeight, Color reqColor, String windowName)
	{
		// Calculate the real position of simple window in desktop.
		int realStartX = (int) Math.round(reqX * width);
		int realStartY = (int) Math.round(reqY * length);
		
		// Create the Simple Window. 
		SimpleWindow newSW = new SimpleWindow(realStartX, realStartY, reqWidth, reqHeight, reqColor);
		// Count simple Window's ID based on Desktop's collection of Simple Window.
		String mapKey = Integer.toString(SWMap.size() + 1);
		newSW.SetID(mapKey);
		SWMap.put(mapKey, newSW);		
		
		// Set Simple Window's Title.
		newSW.SetWindowName(windowName);
	}
	
	
}
