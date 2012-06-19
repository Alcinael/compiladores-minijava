package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class If extends Statement {
  public Exp e;
  public Statement s1,s2;

  public If(Exp ae, Statement as1, Statement as2) {
    e=ae; s1=as1; s2=as2;
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
	this.s1.identifiers(t);
	this.s2.identifiers(t);
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	this.e.removeIdentifiers(t);
	this.s1.removeIdentifiers(t);
	this.s2.removeIdentifiers(t);
	return t;
  }

}