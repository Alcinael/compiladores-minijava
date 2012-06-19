package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class True extends Exp {

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	return t;
  }
}
