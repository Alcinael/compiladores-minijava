package syntaxtree;

import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MainClass {
  public Identifier i1,i2;
  public Statement s;

  public MainClass(Identifier ai1, Identifier ai2, Statement as) {
    i1=ai1; i2=ai2; s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public Table identifiers() {
	Table t = new Table();
	// Incluir Identificadores
	this.i1.identifiers(t);
	this.i2.identifiers(t);
	this.s.identifiers(t);
	// Remover Identificadores
	this.i2.removeIdentifiers(t);
	this.s.removeIdentifiers(t);
	return t;
  }
}
