import java.awt.Color;

public class HelloWorldGraphical 
{	
	private WindowSystem mainWS;
	private SimpleWindow mySimpleWindow;
	private RATLabel resultLabel;
	
	
	public HelloWorldGraphical()
	{
		drawDesktop();
	}
	
	public static void main(String[] args) 
	{
		HelloWorldGraphical hwg = new HelloWorldGraphical();
		hwg.drawHelloWorldGraphical();
	}
	
	private void drawDesktop()
	{
		mainWS = new WindowSystem(1024, 960);
	}
	
	private void drawHelloWorldGraphical()
	{
		mainWS.CreateSimpleWindow(0.25f, 0.25f, 300, 250, "Hello World Application");
		mySimpleWindow = mainWS.GetSimpleWindow("Hello World Application");
		
		RATButton deutschButton = new RATButton(90, 5, 100, 25, Color.yellow);
		deutschButton.SetText("Deutsch", Color.black);
		deutschButton.ActionCommand = "DeutschMorgen";
		deutschButton.AssignListener(new ButtonClickListener());
		mySimpleWindow.AddComponent(deutschButton);
			
		RATButton englishButton = new RATButton(90, 45, 100, 25, Color.cyan);
		englishButton.SetText("English", Color.black);
		englishButton.ActionCommand = "EnglishMorgen";
		englishButton.AssignListener(new ButtonClickListener());
		mySimpleWindow.AddComponent(englishButton);	
		
		RATButton frenchButton = new RATButton(90, 85, 100, 25, Color.green);
		frenchButton.SetText("French", Color.black);
		frenchButton.ActionCommand = "FrenchMorgen";
		frenchButton.AssignListener(new ButtonClickListener());
		mySimpleWindow.AddComponent(frenchButton);
		
		resultLabel = new RATLabel(90, 125, 100, 25, Color.white);
		resultLabel.SetText("Hello All!!", Color.BLACK);
		mySimpleWindow.AddComponent(resultLabel);
		
		RATButton endButton = new RATButton(190, 190, 100, 25, Color.red);
		endButton.SetText("Beenden", Color.white);
		endButton.ActionCommand = "Beenden";
		endButton.AssignListener(new ButtonClickListener());
		mySimpleWindow.AddComponent(endButton);
		
		RATButton resetButton = new RATButton(3, 190, 100, 25, Color.blue);
		resetButton.SetText("Reset", Color.white);
		resetButton.ActionCommand = "Reset";
		resetButton.AssignListener(new ButtonClickListener());
		mySimpleWindow.AddComponent(resetButton);
	}
	
	private class ButtonClickListener implements RATMouseListener
	{
		public void mouseClicked(RATButton inputButton) 
		{
			String command = inputButton.ActionCommand;
			switch (command)
			{
				case "DeutschMorgen":
					resultLabel.BackgroundColor = Color.YELLOW;
					resultLabel.SetText("Guten Morgen", Color.black);
					break;
				
				case "EnglishMorgen":
					resultLabel.BackgroundColor = Color.cyan;
					resultLabel.SetText("Good Morning", Color.black);
					break;
				
				case "FrenchMorgen":
					resultLabel.BackgroundColor = Color.green;
					resultLabel.SetText("Bonjour!", Color.black);
					break;	
					
				case "Reset":
					resultLabel.BackgroundColor = Color.white;
					resultLabel.SetText("Hello All!", Color.black);
					break;						
					
				case "Beenden":
					mainWS.SWMap.remove(mySimpleWindow.ID);
					break;
			}
			
			mainWS.requestRepaint();
		}		
	}
}
