package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class VarDecl {
  public Type t;
  public Identifier i;
  
  public VarDecl(Type at, Identifier ai) {
    t=at; i=ai;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Table identifiers(Table t) {
	Symbol s = Symbol.symbol(i.toString());
	t = t.put(s, s.toString());		
	return t;
  }
}