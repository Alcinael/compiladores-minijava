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
	

	
	/*	
	private Dictionary< Symbol , Stack<Type> > dict = new Hashtable< Symbol , Stack<Type> >();
	private Stack<Symbol> stack = new Stack<Symbol>();
	private Symbol marker = Symbol.symbol("$MARKER$");
	
	public Table() {
	}
	
	public void put(Symbol key, Object value) throws ExceptionDoubleDeclaration {
		
		// Procura para ver se há um dupla declaração sob o mesmo escopo
		int scopestarts = stack.search(marker);
		int keyishere = stack.search(key);
		if( keyishere < scopestarts ){
			throw new ExceptionDoubleDeclaration("Dupla declaração de variável.");
		}
		
		// Acrescenta o tipo na extrutura auxiliar de tipagem
		Stack<Type> stackType = dict.get(key);
		if( stackType == null )
		{
			stackType = new Stack<Type>();
			dict.put(key, stackType);
		}
		
		// Acrescenta o simbolo do identificador a pilha auxiliar de tipagem
		stackType.push((Type)value);
		stack.push(key);
	}
	
	public Object get(Symbol key) throws Exception {
		Stack<Type> pilha = dict.get(key);
		if( pilha == null ) return null;
		if( pilha.isEmpty() ) return null;
		return (Object)pilha.firstElement();
	}
	
	public void beginScope() {
		stack.push(marker);
	}
	public void endScope() {
		Symbol remove;
		Stack<Type> pilha;
		while( !stack.isEmpty() )
		{
			// Remove todos os elementos no escopo corrente
			remove = stack.pop();
			if( remove == marker ){
				break;
			} else {
				// Remove o elemento no topo do encadeamento externo
				pilha = dict.get(remove);
				pilha.pop();
			}
		}			
	}
	
	public java.util.Enumeration<Symbol> keys() {
		// Retorna os symbols do dicionário
		return dict.keys();
	}
*/	
}