import java.awt.*;

public class MyApp {

	public static void main(String[] args) {
		
		// If the Window Manager is enabled, create a Window Manager. If not, create Window System.
		boolean enableWindowManager = true;
		
		if (enableWindowManager == true)
		{
			WindowManager mainWindow = new WindowManager(1024, 960);
			mainWindow.CreateSimpleWindow(0.01f, 0.01f, 350, 150, Color.BLUE, "Water Tribe");
			mainWindow.CreateSimpleWindow(0.30f, 0.30f, 300, 200, Color.RED, "Fire Nation");
			mainWindow.CreateSimpleWindow(0.60f, 0.60f, 400, 200, Color.GREEN, "Air Nomad");
			mainWindow.CreateSimpleWindow(0.01f, 0.60f, 500, 300, Color.yellow, "Earth Kingdom");
		}
		else
		{
			WindowSystem mainWindow = new WindowSystem(1024, 960);
			mainWindow.CreateSimpleWindow(0.01f, 0.01f, 350, 150, Color.BLUE, "Water Tribe");
			mainWindow.CreateSimpleWindow(0.30f, 0.30f, 300, 200, Color.RED, "Fire Nation");
			mainWindow.CreateSimpleWindow(0.60f, 0.60f, 400, 200, Color.GREEN, "Air Nomad");
			mainWindow.CreateSimpleWindow(0.01f, 0.60f, 500, 300, Color.yellow, "Earth Kingdom");
		}
	}
}
