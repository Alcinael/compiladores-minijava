package syntaxtree;

import symbol.Table;
import visitor.Visitor;
import visitor.TypeVisitor;

public class Program {
  public MainClass m;
  public ClassDeclList cl;

  public Program(MainClass am, ClassDeclList acl) {
    m=am; cl=acl; 
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
  
  public Table identifiers()
  {
	  int cont = 0;
	  Table t;
	  t = this.m.identifiers();
	  while(cont < cl.size())
	  {		  
		  t = this.cl.elementAt(cont).identifiers(t);		  
		  cont++;
	  }
	  return t;
  }
  
}