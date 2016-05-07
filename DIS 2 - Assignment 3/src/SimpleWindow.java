import java.awt.*;

public class SimpleWindow 
{
	String ID;
	int width;
	int length;
	int LeftUpperX; 
	int LeftUpperY; 
	Color MyColor;
	
	WindowManagerTab myWMTab; // Each Simple Window have their own WindowManagerTab.
	
	public SimpleWindow(int Xori, int Yori, int inputWidth, int inputLength, Color inputColor)
	{
		LeftUpperX = Xori;
		LeftUpperY = Yori;
		width = inputWidth;
		length = inputLength;
		MyColor = inputColor;
		myWMTab = new WindowManagerTab(Xori, Yori, width, length);
	}		
	
	// Set Simple Window's ID
	public void SetID(String newID)
	{
		if (ID == null)
		{
			ID = newID;
		}
	}
	
	// Set Simple Window's Title.
	public void SetWindowName(String newWindowName)
	{
		myWMTab.SetWindowName(newWindowName);
	}
	
	public void MoveWindow(int deltaX, int deltaY)
	{		
		LeftUpperX -= deltaX;
		LeftUpperY -= deltaY;
		myWMTab.MoveWindow(deltaX, deltaY);
	}
}
