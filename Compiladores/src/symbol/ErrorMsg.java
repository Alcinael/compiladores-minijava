package symbol;

public class ErrorMsg {

	boolean anyErrors;
	
	public void complain(String msg)
	{
		anyErrors = true;
		System.out.println(msg);
	}
	
}
