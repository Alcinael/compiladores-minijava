package syntaxtree;
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
	// Incluir Identificadores
	this.i.identifiers(t);
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