package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ArrayLookup extends Exp {
  public Exp e1,e2;
  
  public ArrayLookup(Exp ae1, Exp ae2) { 
    e1=ae1; e2=ae2;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	  this.e1.identifiers(t);
	  this.e2.identifiers(t);
	  return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	this.e1.removeIdentifiers(t);
	this.e2.removeIdentifiers(t);
	return t;
  }
  
}