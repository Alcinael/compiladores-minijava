package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class NewObject extends Exp {
  public Identifier i;
  
  public NewObject(Identifier ai) {
    i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	if(Symbol.getSymbol(i.toString()) == null)
	{
	  System.out.println("O identificador " + i.toString() + " nao foi declarado");
	}
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	return t;
  }
}