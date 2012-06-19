package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Identifier {
  public String s;

  public Identifier(String as) { 
	s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public String toString(){
    return s;
  }
  
  public Table identifiers(Table t) {
	Symbol s1 = Symbol.symbol(s);
	t.put(s1, s1.toString());
	return t;
  }
  
  public Table removeIdentifiers(Table t) {
	t.remove(s);
	return t;
  }
  
}