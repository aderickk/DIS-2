
public class WindowManagerTab
{
	int LeftUpperX;
	int LeftUpperY;
	int width;
	int length;
	int CloseOptionX; 
	int CloseOptionY;
	String WindowName;
	
	WindowManagerTab(int Xori, int Yori, int inputWidth, int inputLength)
	{
		LeftUpperX = Xori;
		LeftUpperY = Yori;
		width = inputWidth;
		length = 25; // The length of Window Manager is always 25.
		// The width of Close Window is 25, and located at the end of Window Manager Tab's length.
		CloseOptionX = (Xori + width) - 25; 
		CloseOptionY = Yori;
	}
	
	public void SetWindowName(String newWindowName)
	{
		WindowName = newWindowName;
	}
	
	public void MoveWindow(int deltaX, int deltaY)
	{
		LeftUpperX -= deltaX;
		LeftUpperY -= deltaY;
		CloseOptionX = (LeftUpperX + width) - 25;
		CloseOptionY = LeftUpperY;
	}
}
