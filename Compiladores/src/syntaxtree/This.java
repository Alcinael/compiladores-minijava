package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class This extends Exp {
  public Identifier i;
  	
  public This() {
	super();
  }
  
  public This(Identifier i) {
	super();
	this.i = i;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }
}
