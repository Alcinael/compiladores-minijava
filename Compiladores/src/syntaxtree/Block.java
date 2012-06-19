package syntaxtree;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Block extends Statement {
  public StatementList sl;

  public Block(StatementList asl) {
    sl=asl;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	for ( int i = 0; i < sl.size(); i++ ) {
		this.sl.elementAt(i).identifiers(t);
	}
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	for ( int i = 0; i < sl.size(); i++ ) {
		this.sl.elementAt(i).removeIdentifiers(t);
	}
	return t;
  }

}