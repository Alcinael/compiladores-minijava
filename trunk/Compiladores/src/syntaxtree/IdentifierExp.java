package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IdentifierExp extends Exp {
  public String s;
  
  public IdentifierExp(String as) { 
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
    if(Symbol.getSymbol(s) == null)
    {
	  System.out.println("O identificador " + s + " nao foi declarado");
    }
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	return t;
  }
}
