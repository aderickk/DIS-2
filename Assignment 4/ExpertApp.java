import java.awt.Color;

public class ExpertApp {

	private WindowSystem mainWS;				// The desktop.
	private SimpleWindow mySimpleWindow;		// The window where calculator located.
	private RATLabel resultLabel;				// label where result displayed.
	private Boolean isHitNumber;				// indicate whether a number just been hit or not.
	private double tempResult;					// result that will be displayed in resultLabel.
	private RATLabel infoLabel;					// label to indicate what operator will be done.
	private String lastOperator;				// save the last operator hit.
	private Boolean isDouble;					// indicate whether a decimal is activated or not.
	
	private static int ButtonSize = 30;
	
	/*
	 * Constructor. Create the desktop (Window System) and Set all variables to default value.
	 */
	public ExpertApp()
	{
		drawDesktop();
		isHitNumber = true;
		tempResult = 0;
		lastOperator = "";
		isDouble = false;
	}
	
	public static void main(String[] args) 
	{
		ExpertApp calculator = new ExpertApp();
		calculator.drawCalculatorBody();
		calculator.drawAllButtons();
	}
	
	/*
	 * create and assign the Window System.
	 */
	private void drawDesktop()
	{
		mainWS = new WindowSystem(1024, 960);
	}
	
	/*
	 * Draw the calculator body and result label.
	 */
	private void drawCalculatorBody()
	{
		mainWS.CreateSimpleWindow(0.25f, 0.25f, 190, 275, "RATulator");
		mySimpleWindow = mainWS.GetSimpleWindow("RATulator");		
		
		resultLabel = new RATLabel(20, 20, 150, 20, Color.white);
		resultLabel.SetText("0", Color.BLACK);
		mySimpleWindow.AddComponent(resultLabel);
	}
	
	/*
	 * Draw all number buttons and operator buttons.
	 */
	private void drawAllButtons()
	{		
		drawNumbers();
		drawOperator();		
	}
	
	/*
	 * Draw number buttons.
	 */
	private void drawNumbers()
	{
		// First Line
		RATButton sevenButton = new RATButton(20, 50, ButtonSize, ButtonSize, Color.cyan);
		sevenButton.SetText("7", Color.black);
		sevenButton.ActionCommand = "DigitSeven";
		sevenButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(sevenButton);
		
		RATButton eightButton = new RATButton(60, 50, ButtonSize, ButtonSize, Color.cyan);
		eightButton.SetText("8", Color.black);
		eightButton.ActionCommand = "DigitEight";
		eightButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(eightButton);
		
		RATButton nineButton = new RATButton(100, 50, ButtonSize, ButtonSize, Color.cyan);
		nineButton.SetText("9", Color.black);
		nineButton.ActionCommand = "DigitNine";
		nineButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(nineButton);
		
		// Second Line
		RATButton fourButton = new RATButton(20, 90, ButtonSize, ButtonSize, Color.cyan);
		fourButton.SetText("4", Color.black);
		fourButton.ActionCommand = "DigitFour";
		fourButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(fourButton);
				
		RATButton fiveButton = new RATButton(60, 90, ButtonSize, ButtonSize, Color.cyan);
		fiveButton.SetText("5", Color.black);
		fiveButton.ActionCommand = "DigitFive";
		fiveButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(fiveButton);
				
		RATButton sixButton = new RATButton(100, 90, ButtonSize, ButtonSize, Color.cyan);
		sixButton.SetText("6", Color.black);
		sixButton.ActionCommand = "DigitSix";
		sixButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(sixButton);
		
		// Third Line
		RATButton oneButton = new RATButton(20, 130, ButtonSize, ButtonSize, Color.cyan);
		oneButton.SetText("1", Color.black);
		oneButton.ActionCommand = "DigitOne";
		oneButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(oneButton);
						
		RATButton twoButton = new RATButton(60, 130, ButtonSize, ButtonSize, Color.cyan);
		twoButton.SetText("2", Color.black);
		twoButton.ActionCommand = "DigitTwo";
		twoButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(twoButton);
						
		RATButton threeButton = new RATButton(100, 130, ButtonSize, ButtonSize, Color.cyan);
		threeButton.SetText("3", Color.black);
		threeButton.ActionCommand = "DigitThree";
		threeButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(threeButton);
		
		// Zero Line
		RATButton zeroButton = new RATButton(60, 170, ButtonSize, ButtonSize, Color.cyan);
		zeroButton.SetText("0", Color.black);
		zeroButton.ActionCommand = "DigitZero";
		zeroButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(zeroButton);
		
	}
	
	/*
	 * Draw operator Buttons.
	 */
	private void drawOperator()
	{
		// Clear Button
		RATButton clearButton = new RATButton(20, 210, 70, ButtonSize, Color.orange);
		clearButton.SetText("Clear", Color.black);
		clearButton.ActionCommand = "Clear";
		clearButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(clearButton);
		
		// Comma Button
		RATButton commaButton = new RATButton(20, 170, ButtonSize, ButtonSize, Color.orange);
		commaButton.SetText(".", Color.black);
		commaButton.ActionCommand = "Comma";
		commaButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(commaButton);
		
		// Equal Button
		RATButton equalButton = new RATButton(100, 170, ButtonSize, ButtonSize, Color.orange);
		equalButton.SetText("=", Color.black);
		equalButton.ActionCommand = "Equal";
		equalButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(equalButton);		
		
		// Plus Button
		RATButton plusButton = new RATButton(140, 170, ButtonSize, ButtonSize, Color.orange);
		plusButton.SetText("+", Color.black);
		plusButton.ActionCommand = "Plus";
		plusButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(plusButton);
		
		// Minus Button
		RATButton minusButton = new RATButton(140, 130, ButtonSize, ButtonSize, Color.orange);
		minusButton.SetText("-", Color.black);
		minusButton.ActionCommand = "Minus";
		minusButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(minusButton);
		
		// Multiply Button
		RATButton multiplyButton = new RATButton(140, 90, ButtonSize, ButtonSize, Color.orange);
		multiplyButton.SetText("*", Color.black);
		multiplyButton.ActionCommand = "Multiply";
		multiplyButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(multiplyButton);
		
		// Divide Button
		RATButton divideButton = new RATButton(140, 50, ButtonSize, ButtonSize, Color.orange);
		divideButton.SetText("/", Color.black);
		divideButton.ActionCommand = "Divide";
		divideButton.AssignListener(new CalcClickListener());
		mySimpleWindow.AddComponent(divideButton);		
		
		// Info Label
		infoLabel = new RATLabel(100, 210, 70, ButtonSize, Color.LIGHT_GRAY);
		infoLabel.SetText("", Color.red);
		mySimpleWindow.AddComponent(infoLabel);
	}
	
	/*
	 * Do mathematical process when an operator is hit.
	 */
	public void HitOperator(String input)
	{
		lastOperator = input;
		
		switch(input)
		{
			case "Plus":
				infoLabel.SetText("PLUS!!", Color.red);
				isHitNumber = false;
				tempResult += Double.parseDouble(resultLabel.TextLabel);						
				break;
			
			case "Minus":
				infoLabel.SetText("Minus!!", Color.red);
				isHitNumber = false;
				if (tempResult != 0)
				{					
					tempResult = tempResult - Double.parseDouble(resultLabel.TextLabel);	
				}
				else
				{
					tempResult = Double.parseDouble(resultLabel.TextLabel);
				}						
				break;
			
			case "Multiply":
				infoLabel.SetText("Multiply", Color.red);
				isHitNumber = false;
				if (tempResult != 0)
				{					
					tempResult = tempResult * Double.parseDouble(resultLabel.TextLabel);	
				}
				else
				{
					tempResult = Double.parseDouble(resultLabel.TextLabel);
				}
				break;
			
			case "Divide":
				infoLabel.SetText("Divide", Color.red);
				isHitNumber = false;
				if (tempResult != 0)
				{					
					tempResult = tempResult / Double.parseDouble(resultLabel.TextLabel);
				}
				else
				{
					tempResult = Double.parseDouble(resultLabel.TextLabel);
				}
				break;
		}
	}
	
	/*
	 * Append the current string with button value. 
	 */
	public void HitNumber(int input)
	{
		infoLabel.SetText("", Color.red);
		
		if(resultLabel.TextLabel == "0" || isHitNumber == false)
		{
			// If the current label is 0, or an operator just been hit, replace the current display.
			resultLabel.SetText(Integer.toString(input), Color.black);
		}
		else
		{
			// other way, just append the string.
			String newText = resultLabel.TextLabel + input;
			resultLabel.SetText(newText, Color.black);
		}
				
		isHitNumber = true;
	}
	
	/*
	 * Just like swing, the event listener is implemented by the class that need to overwrite it.
	 * The event listener will listen to the button that being hit.
	 */
	private class CalcClickListener implements RATMouseListener
	{
		public void mouseClicked(RATButton inputButton) 
		{
			String command = inputButton.ActionCommand;
			switch (command)
			{
				case "DigitZero":
					HitNumber(0);
					break;
				
				case "DigitOne":
					HitNumber(1);
					break;
					
				case "DigitTwo":
					HitNumber(2);
					break;
					
				case "DigitThree":
					HitNumber(3);
					break;
					
				case "DigitFour":
					HitNumber(4);
					break;
					
				case "DigitFive":
					HitNumber(5);
					break;
					
				case "DigitSix":
					HitNumber(6);
					break;
					
				case "DigitSeven":
					HitNumber(7);
					break;
					
				case "DigitEight":
					HitNumber(8);
					break;
					
				case "DigitNine":
					HitNumber(9);
					break;
					
				case "Clear":
					infoLabel.SetText("", Color.red);
					resultLabel.SetText("0", Color.black);
					tempResult = 0;
					lastOperator = "";
					isHitNumber = true;
					isDouble = false;
					break;
					
				case "Equal":
					HitOperator(lastOperator);
					infoLabel.SetText("", Color.red);
					if(isDouble == true)
					{
						resultLabel.SetText(Double.toString(tempResult), Color.black);
					}
					else
					{
						resultLabel.SetText(Integer.toString((int)tempResult), Color.black);
					}
					lastOperator = "";
					tempResult = 0;
					break;
					
				case "Comma":
					if (!resultLabel.TextLabel.contains("."))
					{
						resultLabel.TextLabel += ".";
					}
					isDouble = true;
					isHitNumber = true;
					break;							
				
				default:
					HitOperator(command);
					break;				
			}
			
			mainWS.requestRepaint();
		}		
	}
	
}
