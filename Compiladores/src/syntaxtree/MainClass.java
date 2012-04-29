package syntaxtree;

import symbol.Symbol;
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
	Symbol s = Symbol.symbol(i1.toString());
	t = t.put(s, s.toString());
	Symbol s1 = Symbol.symbol(i2.toString());
	t = t.put(s1, s1.toString());
	return t;
  }
}
