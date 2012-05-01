package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;

public class IntegerType extends Type {
  public int value;
	
  public IntegerType() {
	super();
	// TODO Auto-generated constructor stub
  }
  
  public IntegerType(int value) {
	super();
	this.value = value;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

}