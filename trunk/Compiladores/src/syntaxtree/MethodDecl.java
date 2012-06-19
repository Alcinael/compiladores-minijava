package syntaxtree;
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
	// Incluir Identificadores
	this.i.identifiers(t);
	while(cont < fl.size())
	{
	  this.fl.elementAt(cont).identifiers(t);		  
	  cont++;
	}
	cont = 0;
	while(cont < vl.size())
	{		  
	  this.vl.elementAt(cont).identifiers(t);		  
	  cont++;
	}
	cont = 0;
	while(cont < sl.size())
	{		  
	  this.sl.elementAt(cont).identifiers(t);	  
	  cont++;
	}
	this.e.identifiers(t);
	// Remover Identificadores
	cont = 0;
	while(cont < fl.size())
	{
	  this.fl.elementAt(cont).removeIdentifiers(t);		  
	  cont++;
	}
	cont = 0;
	while(cont < vl.size())
	{		  
	  this.vl.elementAt(cont).removeIdentifiers(t);		  
	  cont++;
	}
	cont = 0;
	while(cont < sl.size())
	{		  
	  this.sl.elementAt(cont).removeIdentifiers(t);	  
	  cont++;
	}
	this.e.removeIdentifiers(t);
	return t;
  }
  
}