package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Print extends Statement {
  public Exp e;

  public Print(Exp ae) {
    e=ae; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	  this.e.identifiers(t);
	  return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	  this.e.removeIdentifiers(t);
	  return t;
  }

}