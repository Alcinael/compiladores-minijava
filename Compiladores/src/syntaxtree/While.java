package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class While extends Statement {
  public Exp e;
  public Statement s;

  public While(Exp ae, Statement as) {
    e=ae; s=as; 
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
	this.s.identifiers(t);
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	this.e.removeIdentifiers(t);
	this.s.removeIdentifiers(t);
	return t;
  }

}