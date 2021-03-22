import java.util.*;
public class Calculate24		//Goal is to confirm whether 4 numbers can equal 24, not print the solution
{
	public static final String AWPS = "+-*/~|";	//(a,b), then ~ is b-a and | is b/a, create my own key
	public static void main(String[] args)
	{
		equal24(20, 10, 3, 2);	//20 + 10 - 3 * 2
	}
	public static void equal24(int a, int b, int c, int d)
	{
		if(combos(a, b, c, d) == true)
			System.out.println(a + " " + b + " " + c + " " + d + " equal 24!");
		else
			System.out.println("They do NOT equal 24!");
	}
	public static boolean RPN(String eq)
	{
		int[] spaces = new int[7];
		Stack<Integer> ans = new Stack<Integer>();
		int x = 0;
		String c;
		for(int i = 0; i < eq.length(); i++)
		{
			if(eq.charAt(i) == ' ')					//whitespace
			{
				spaces[x] = i;
				x++;
			}
		}
		int start = 0, end;						//sp = spaces pointer
		for(int i = 0; i < spaces.length; i++)
		{
			end = spaces[i];
			c = eq.substring(start, end);
			if(isOP(c))	//must be operator
			{
				System.out.println("TEST 1");
				int b = ans.pop();
				int a = ans.pop();
				int num = awp(a, c, b);
				if(num == -1)	//error check, unrecognized symbol
					return false;
				else
				{
					
					ans.push(num);
					ans.peek();
				}
			}
			else				//it's a number
			{
				int newC = 0;
				for(int u = 0; u < c.length(); u++)
				{
					newC *= 10;
					newC += c.charAt(u) - '0';
				}
				ans.push(newC);
				ans.peek();
			}
			start = end+1;
		}
		if(ans.pop() == 24)
			return true;
		return false;
	}
	public static boolean isOP(String c)
	{
		if(c == "+" || c == "-" || c == "*" || c == "/" || c == "~" || c == "|")
			return true;
		return false;
	}
	public static int awp(int a, String op, int b)
	{
		if(op == "+")
			return(a+b);
		else if(op == "-")
			return(a-b);
		else if(op == "*")
			return(a*b);
		else if(op == "/")
			return(a/b);
		else if(op == "~")
			return(b-a);
		else if(op == "|")
			return(b/a);
		else
			return -1;
	}
	public static boolean combos(int a, int b, int c, int d)
	{
		int[] list = new int[] {a, b, c, d};
		String test;
		for(int e = 0; e < list.length; e++)						//1st num
		{
			for(int f = 0; f < list.length; f++)					//2nd num
			{
				if(f == e)
					continue;
				for(int g = 0; g < list.length; g++)				//3rd num
				{
					if(g == e || g == f)
						continue;
					for(int h = 0; h < list.length; h++)			//4th num
					{
						if(h == e || h == f || h == g)
							continue;
						for(int i = 0; i < AWPS.length(); i++)		//i j k are 3 possible operators (+ + +), (+ - *) etc
						{
							for(int j = 0; j < AWPS.length(); j++)
							{
								for(int k = 0; k < AWPS.length(); k++)
								{
									test = "";
									test += list[e];
									test += " ";
									test += list[f];
									test += " ";
									test += list[g];
									test += " ";
									test += list[h];
									test += " ";
									test += AWPS.charAt(i);
									test += " ";
									test += AWPS.charAt(j);
									test += " ";
									test += AWPS.charAt(k);
									test += " ";
									String TESTING = "24 6 2 4 + + + ";														//TESTING
									if(RPN(TESTING))																		//TESTING
										return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
}
