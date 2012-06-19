package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Call extends Exp {
  public Exp e;
  public Identifier i;
  public ExpList el;
  
  public Call(Exp ae, Identifier ai, ExpList ael) {
    e=ae; i=ai; el=ael;
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
    if(Symbol.getSymbol(i.toString()) == null)
    {
	  System.out.println("O identificador " + i.toString() + " nao foi declarado");
    }
	for ( int i = 0; i < el.size(); i++ ) {
		this.el.elementAt(i).identifiers(t);
	}    
	return t;
  }

  @Override
  public Table removeIdentifiers(Table t) {
	this.e.removeIdentifiers(t);
	for ( int i = 0; i < el.size(); i++ ) {
		this.el.elementAt(i).removeIdentifiers(t);
	}	
	return t;
  }
}
