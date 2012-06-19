package symbol;

import java.util.Enumeration;
import symbol.Symbol;

public class Table {
	
	public Enumeration<Symbol> keys;
	
	public Table()
	{

	}
	
	public Table put(Symbol key, Object value)
	{
		Symbol symbol = Symbol.symbol(String.valueOf(value));
		keys = symbol.copiar(keys);
		return this;
	}

	public Table remove(Object value)
	{
		Symbol.removeSymbol(String.valueOf(value));
		return this;
	}
	
	public Object get(Symbol key)
	{
		Symbol s;
		String resultado = null;
		while(keys.hasMoreElements() == true)
		{
			s = keys.nextElement(); 
			if(s.toString() == key.toString())
			{
				resultado = s.toString();
			}
		}
		return resultado;
	}
	
	public void listar()
	{
		System.out.print("\nIdentificadores: ");
		while(keys.hasMoreElements())
		{
			System.out.print(keys.nextElement().toString());
			if(keys.hasMoreElements())
			{
				System.out.print(", ");
			}
		}
		System.out.println();
	}
	
}