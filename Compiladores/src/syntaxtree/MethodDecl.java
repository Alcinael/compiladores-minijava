package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class MethodDecl {
  public Type t;
  public Identifier i;
  public FormalList fl;
  public VarDeclList vl;
  public StatementList sl;
  public Exp e;

  public MethodDecl(Type at, Identifier ai, FormalList afl, VarDeclList avl, 
                    StatementList asl, Exp ae) {
    t=at; i=ai; fl=afl; vl=avl; sl=asl; e=ae;
  }
 
  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Table identifiers(Table t) {
	int cont = 0;
	Symbol s = Symbol.symbol(i.toString());
	t = t.put(s, s.toString());
	while(cont < fl.size())
	{		  
	  t = this.fl.elementAt(cont).identifiers(t);		  
	  cont++;
	}
	return t;
  }
  
}