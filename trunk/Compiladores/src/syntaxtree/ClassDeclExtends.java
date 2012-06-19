package syntaxtree;
import symbol.Symbol;
import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class ClassDeclExtends extends ClassDecl {
  public Identifier i;
  public Identifier j;
  public VarDeclList vl;  
  public MethodDeclList ml;
 
  public ClassDeclExtends(Identifier ai, Identifier aj, 
                  VarDeclList avl, MethodDeclList aml) {
    i=ai; j=aj; vl=avl; ml=aml;
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
	  this.i.identifiers(t);
	  if(Symbol.getSymbol(j.toString()) == null)
	  {
		System.out.println("O identificador " + j.toString() + " nao foi declarado");
	  }else
	  {
		  if(Symbol.getSymbol(j.toString()) != null)
		  {
			  if(j.toString().equals(i.toString()))
			  {
				  System.out.println("A classe nao pode herdar dela propria");
			  }
		  }
	  }
	  while(cont < vl.size())
	  {		  
		this.vl.elementAt(cont).identifiers(t);		  
		cont++;
	  }
	  cont = 0;
	  while(cont < ml.size())
	  {		  
		this.ml.elementAt(cont).identifiers(t);		  
		cont++;
	  }
  	  // Remover Identificadores
	  cont = 0;
	  while(cont < vl.size())
	  {
	    this.vl.elementAt(cont).removeIdentifiers(t);
	    cont++;
	  }
	  cont = 0;
	  while(cont < ml.size())
	  {
  	    this.ml.elementAt(cont).i.removeIdentifiers(t);
	    cont++;
	  }
	  return t;
    }
}
