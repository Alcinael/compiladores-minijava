package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Formal {
  public Type t;
  public Identifier i;
 
  public Formal(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public Table identifiers(Table t) {
	this.i.identifiers(t);
	return t;
  }
  
  public Table removeIdentifiers(Table t) {
	this.i.removeIdentifiers(t);
	return t;
  }
  
}