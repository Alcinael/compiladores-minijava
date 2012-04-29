package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclSimple extends ClassDecl {
  public Identifier i;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclSimple(Identifier ai, VarDeclList avl, MethodDeclList aml) {
    i=ai; vl=avl; ml=aml;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  @Override
  public Table identifiers(Table t) {
	int cont = 0;
	Symbol s = Symbol.symbol(i.toString());
	t = t.put(s, s.toString());
	while(cont < vl.size())
	{		  
	  t = this.vl.elementAt(cont).identifiers(t);		  
	  cont++;
	}
	cont = 0;
	while(cont < ml.size())
	{		  
	  t = this.ml.elementAt(cont).identifiers(t);		  
	  cont++;
	}
	return t;
  }

}