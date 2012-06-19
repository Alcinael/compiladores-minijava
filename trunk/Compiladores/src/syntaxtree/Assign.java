package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Assign extends Statement {
  public Identifier i;
  public Exp e;

  public Assign(Identifier ai, Exp ae) {
    i=ai; e=ae; 
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
	  this.e.identifiers(t);
	  return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	  this.e.removeIdentifiers(t);
	  return t;
  }

}