import java.awt.Color;

public class ExpertApp {

	private WindowSystem mainWS;
	private SimpleWindow mySimpleWindow;
	private RATLabel resultLabel;	
	private Boolean isHitNumber;
	private int tempResult;
	private RATLabel infoLabel;
	private String lastOperator;
	
	private static int ButtonSize = 30;
	
	public ExpertApp()
	{
		drawDesktop();
		isHitNumber = true;
		tempResult = 0;
		lastOperator = "";
	}
	
	public static void main(String[] args) 
	{
		ExpertApp calculator = new ExpertApp();
		calculator.drawCalculatorBody();
		calculator.drawAllButtons();
	}
	
	private void drawDesktop()
	{
		mainWS = new WindowSystem(1024, 960);
	}
	
	private void drawCalculatorBody()
	{
		mainWS.CreateSimpleWindow(0.25f, 0.25f, 190, 275, "RATulator");
		mySimpleWindow = mainWS.GetSimpleWindow("RATulator");		
		
		resultLabel = new RATLabel(20, 20, 150, 20, Color.white);
		resultLabel.SetText("0", Color.BLACK);
		mySimpleWindow.AddComponent(resultLabel);
	}
	
	private void drawAllButtons()
	{		
		drawNumbers();
		drawOperator();		
	}
	
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
		minusButton.ActionCommand = "Equal";
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
	
	public void HitOperator(String input)
	{
		lastOperator = input;
		
		switch(input)
		{
			case "Plus":
				infoLabel.SetText("PLUS!!", Color.red);
				isHitNumber = false;
				tempResult += Integer.parseInt(resultLabel.TextLabel);
				break;
			
			case "Minus":
				infoLabel.SetText("Minus!!", Color.red);
				isHitNumber = false;
				tempResult -= Integer.parseInt(resultLabel.TextLabel);
				break;
			
			case "Multiply":
				infoLabel.SetText("Multiply", Color.red);
				isHitNumber = false;
				if (tempResult != 0)
				{
					tempResult = tempResult * Integer.parseInt(resultLabel.TextLabel);	
				}
				else
				{
					tempResult = Integer.parseInt(resultLabel.TextLabel);
				}
				break;
			
			case "Divide":
				infoLabel.SetText("Divide", Color.red);
				isHitNumber = false;
				if (tempResult != 0)
				{					
					tempResult = tempResult / Integer.parseInt(resultLabel.TextLabel);
				}
				else
				{
					tempResult = Integer.parseInt(resultLabel.TextLabel);
				}
				break;
		}
	}
	
	public void HitNumber(int input)
	{
		infoLabel.SetText("", Color.red);
		if(resultLabel.TextLabel == "0" || isHitNumber == false)
		{
			resultLabel.SetText(Integer.toString(input), Color.black);
		}
		else
		{
			String newText = resultLabel.TextLabel + input;
			resultLabel.SetText(newText, Color.black);
		}
		
		isHitNumber = true;
	}
	
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
					break;
					
				case "Equal":
					HitOperator(lastOperator);
					infoLabel.SetText("", Color.red);
					resultLabel.SetText(Integer.toString(tempResult), Color.black);
					lastOperator = "";
					tempResult = 0;
					break;
					
				default:
					HitOperator(command);
					break;				
			}
			
			mainWS.requestRepaint();
		}		
	}
	
}
