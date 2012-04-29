package symbol;

import java.util.Enumeration;

public class Symbol {

	private String name;
	
	private static java.util.Dictionary<String, Symbol> dict = new java.util.Hashtable<String, Symbol>();
	
	private Symbol(String n)
	{
		name = n;
	}
	
	public String toString()
	{
		return name;
	}
	
	public Enumeration<Symbol> copiar(Enumeration<Symbol> keys)
	{
		keys = dict.elements();
		return keys;
	}
	
	public static Symbol symbol(String n)
	{
		String u = n.intern();
		Symbol s = (Symbol) dict.get(u);
		if(s == null)
		{
			s = new Symbol(u);
			dict.put(u, s);
		}
		return s;
	}
	
}