package visitor;

import symbol.ErrorMsg;
import syntaxtree.And;
import syntaxtree.ArrayAssign;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.Assign;
import syntaxtree.Block;
import syntaxtree.BooleanType;
import syntaxtree.Call;
import syntaxtree.ClassDeclExtends;
import syntaxtree.ClassDeclSimple;
import syntaxtree.False;
import syntaxtree.Formal;
import syntaxtree.Identifier;
import syntaxtree.IdentifierExp;
import syntaxtree.IdentifierType;
import syntaxtree.If;
import syntaxtree.IntArrayType;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.LessThan;
import syntaxtree.MainClass;
import syntaxtree.MethodDecl;
import syntaxtree.Minus;
import syntaxtree.NewArray;
import syntaxtree.NewObject;
import syntaxtree.Not;
import syntaxtree.Plus;
import syntaxtree.Print;
import syntaxtree.Program;
import syntaxtree.This;
import syntaxtree.Times;
import syntaxtree.True;
import syntaxtree.Type;
import syntaxtree.VarDecl;
import syntaxtree.While;

public class TypeCheckingVisitor implements TypeVisitor {

	@Override
	public Type visit(Program n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.m.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Program must be of type MainClass");
		}
		for ( int i = 0; i < n.cl.size(); i++ ) {
			if(! (n.cl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Class must be of type identifier");
			}
		}
		return n.m.accept(this);
	}

	@Override
	public Type visit(MainClass n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i1.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of MainClass must be of type identifier");
		}
		if(! (n.i2.accept(this) instanceof IdentifierType) )
		{
			error.complain("Second term of MainClass must be of type identifier");
		}
		if(! (n.s.accept(this) instanceof BooleanType ||
			  n.s.accept(this) instanceof IntegerType ||
			  n.s.accept(this) instanceof IdentifierType ||
			  n.s.accept(this) instanceof IntArrayType) )
		{
			error.complain("Third term of MainClass must be of type boolean or integer or identifier or array of integers");
		}
		return n.i1.accept(this);
	}

	@Override
	public Type visit(ClassDeclSimple n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Class must be of type identifier");
		}
		for ( int i = 0; i < n.vl.size(); i++ ) {
			if(! (n.vl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Second term of Class must be of type identifier");
			}
		}
		for ( int i = 0; i < n.ml.size(); i++ ) {
			if(! (n.ml.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Third term of Class must be of type identifier");
			}
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(ClassDeclExtends n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Class must be of type identifier");
		}
		if(! (n.j.accept(this) instanceof IdentifierType) )
		{
			error.complain("Second term of Class must be of type identifier");
		}
		for ( int i = 0; i < n.vl.size(); i++ ) {
			if(! (n.vl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Third term of Class must be of type identifier");
			}
		}
		for ( int i = 0; i < n.ml.size(); i++ ) {
			if(! (n.ml.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Fourth term of Class must be of type identifier");
			}
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(VarDecl n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.t.accept(this) instanceof IntArrayType ||
			  n.t.accept(this) instanceof BooleanType ||
			  n.t.accept(this) instanceof IntegerType ||
			  n.t.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Declaration of the Variable must be of type array of integers or boolean or integer or identifier");
		}
		if(! (n.i.accept(this) instanceof IdentifierType) )
			{
				error.complain("Second term of Declaration of the Variable must be of type identifier");
			}
		return n.i.accept(this);
	}

	@Override
	public Type visit(MethodDecl n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.t.accept(this) instanceof IntArrayType ||
			  n.t.accept(this) instanceof BooleanType ||
			  n.t.accept(this) instanceof IntegerType ||
			  n.t.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Declaration of the Method must be of type array of integers or boolean or integer or identifier");
		}
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Second term of Declaration of the Method must be of type identifier");
		}
		for ( int i = 0; i < n.fl.size(); i++ ) {
			if(! (n.fl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Third term of Declaration of the Method must be of type identifier");
			}
		}
		for ( int i = 0; i < n.vl.size(); i++ ) {
			if(! (n.vl.elementAt(i).accept(this) instanceof BooleanType ||
				  n.vl.elementAt(i).accept(this) instanceof IntegerType ||
				  n.vl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Fourth term of Declaration of the Method must be of type identifier");
			}
		}
		for ( int i = 0; i < n.sl.size(); i++ ) {
			if(! (n.sl.elementAt(i).accept(this) instanceof BooleanType ||
				  n.sl.elementAt(i).accept(this) instanceof IntegerType ||
				  n.sl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Fifth term of Declaration of the Method must be of type identifier");
			}
		}
		if(! (n.e.accept(this) instanceof BooleanType ||
			  n.e.accept(this) instanceof IntegerType ||
			  n.e.accept(this) instanceof IdentifierType) )
		{
			error.complain("Sixth term of Declaration of the Method must be of type boolean or integer or identifier");
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(Formal n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.t.accept(this) instanceof IntArrayType ||
			  n.t.accept(this) instanceof BooleanType ||
			  n.t.accept(this) instanceof IntegerType ||
			  n.t.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Formal must be of type array of integers or boolean or integer or identifier");
		}
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Second term of Formal must be of type identifier");
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(IntArrayType n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof IntArrayType) )
		{
			error.complain("Type must be of type array of integers");
		}
		return new IntArrayType();
	}

	@Override
	public Type visit(BooleanType n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof BooleanType) )
		{
			error.complain("Type must be of type boolean");
		}
		return new BooleanType();
	}

	@Override
	public Type visit(IntegerType n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof IntegerType) )
		{
			error.complain("Type must be of type integer");
		}
		return new IntegerType();
	}

	@Override
	public Type visit(IdentifierType n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.s instanceof String) )
		{
			error.complain("Identifier must be of type string");
		}
		return new IdentifierType(n.s);
	}

	@Override
	public Type visit(Block n) {
		ErrorMsg error = new ErrorMsg();
		for ( int i = 0; i < n.sl.size(); i++ ) {
			if(! (n.sl.elementAt(i).accept(this) instanceof IntegerType ||
				  n.sl.elementAt(i).accept(this) instanceof BooleanType ||
				  n.sl.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Exp must be of type integer or boolean or identifier");
			}
		}
		return n.sl.elementAt(0).accept(this);
	}

	@Override
	public Type visit(If n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof BooleanType) )
		{
			error.complain("Exp must be of type boolean");
		}
		if(! (n.s1.accept(this) instanceof BooleanType ||
			  n.s1.accept(this) instanceof IntegerType ||
			  n.s1.accept(this) instanceof IdentifierType) )
		{
			error.complain("Statement must be of type boolean or integer or identifier");
		}
		if(! (n.s2.accept(this) instanceof BooleanType ||
			  n.s2.accept(this) instanceof IntegerType ||
			  n.s2.accept(this) instanceof IdentifierType) )
		{
			error.complain("Statement must be of type boolean or integer or identifier");
		}		
		return n.e.accept(this);
	}

	@Override
	public Type visit(While n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof BooleanType) )
		{
			error.complain("Exp must be of type boolean or integer or identifier");
		}
		if(! (n.s.accept(this) instanceof BooleanType ||
			  n.s.accept(this) instanceof IntegerType ||
			  n.s.accept(this) instanceof IdentifierType) )
		{
			error.complain("Statement must be of type boolean or integer or identifier");
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(Print n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof BooleanType ||
			  n.e.accept(this) instanceof IntegerType ||
			  n.e.accept(this) instanceof IdentifierType) )
		{
			error.complain("Exp must be of type boolean or integer or identifier");
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(Assign n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Left side of Assign must be of type identifier");
		}
		if(! (n.e.accept(this) instanceof BooleanType ||
			  n.e.accept(this) instanceof IntegerType ||
			  n.e.accept(this) instanceof IdentifierType ||
			  n.e.accept(this) instanceof IntArrayType) )
		{
			error.complain("Right side of Assign must be of type boolean or integer or identifier or array of integers");
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(ArrayAssign n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("First term of Array Assign must be of type identifier");
		}
		if(! (n.e1.accept(this) instanceof IntegerType) )
		{
			error.complain("Second term of Array Assign must be of type integer");
		}
		if(! (n.e2.accept(this) instanceof BooleanType ||
			  n.e2.accept(this) instanceof IntegerType ||
			  n.e2.accept(this) instanceof IdentifierType) )
		{
			error.complain("Third term of Array Assign must be of type boolean or integer or identifier");
		}
		return n.i.accept(this);	
	}

	@Override
	public Type visit(And n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof BooleanType) )
		{
			error.complain("Left side of Plus must be of type boolean");
		}
		if(! (n.e2.accept(this) instanceof BooleanType) )
		{
			error.complain("Right side of Plus must be of type boolean");
		}
		return n.e1.accept(this);
	}

	@Override
	public Type visit(LessThan n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof IdentifierType) )
		{
			error.complain("Left side of LessThan must be of type identifier");
		}
		if(! (n.e2.accept(this) instanceof IntegerType) )
		{
			error.complain("Right side of LessThan must be of type integer");
		}
		return n.e1.accept(this);
	}

	@Override
	public Type visit(Plus n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof IntegerType) )
		{
			error.complain("Left side of Plus must be of type integer");
		}
		if(! (n.e2.accept(this) instanceof IntegerType) )
		{
			error.complain("Right side of Plus must be of type integer");
		}
		return n.e1.accept(this);
	}

	@Override
	public Type visit(Minus n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof IntegerType) )
		{
			error.complain("Left side of Plus must be of type integer");
		}
		if(! (n.e2.accept(this) instanceof IntegerType) )
		{
			error.complain("Right side of Plus must be of type integer");
		}
		return n.e1.accept(this);
	}

	@Override
	public Type visit(Times n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof IntegerType) )
		{
			error.complain("Left side of Plus must be of type integer");
		}
		if(! (n.e2.accept(this) instanceof IntegerType) )
		{
			error.complain("Right side of Plus must be of type integer");
		}
		return n.e1.accept(this);	
	}

	@Override
	public Type visit(ArrayLookup n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e1.accept(this) instanceof IdentifierType) )
		{
			error.complain("Exp must be of type identifier");
		}
		if(! (n.e2.accept(this) instanceof IntegerType) )
		{
			error.complain("Exp must be of type integer");
		}
		return n.e1.accept(this);
	}

	@Override
	public Type visit(ArrayLength n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof IdentifierType) )
		{
			error.complain("Exp must be of type identifier");
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(Call n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof IdentifierType) )
		{
			error.complain("Class must be of type identifier");
		}
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Method must be of type identifier");
		}
		for ( int i = 0; i < n.el.size(); i++ ) {
			if(! (n.el.elementAt(i).accept(this) instanceof IntegerType ||
				  n.el.elementAt(i).accept(this) instanceof BooleanType ||
				  n.el.elementAt(i).accept(this) instanceof IdentifierType) )
			{
				error.complain("Exp must be of type integer or boolean or identifier or array of the int");
			}
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(IntegerLiteral n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof IntegerLiteral) )
		{
			error.complain("Exp must be of type integer");
		}
		return new IntegerType();	
	}

	@Override
	public Type visit(True n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof True) )
		{
			error.complain("Exp must be of type boolean");
		}
		return new BooleanType();
	}

	@Override
	public Type visit(False n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n instanceof False) )
		{
			error.complain("Exp must be of type boolean");
		}
		return new BooleanType();
	}

	@Override
	public Type visit(IdentifierExp n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.s instanceof String) )
		{
			error.complain("Identifier must be of type string");
		}
		return new IdentifierType(n.s);
	}

	@Override
	public Type visit(This n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Exp must be of type this");
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(NewArray n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof IntegerType) )
		{
			error.complain("Exp must be of type integer");
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(NewObject n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.i.accept(this) instanceof IdentifierType) )
		{
			error.complain("Exp must be of type identifier");
		}
		return n.i.accept(this);
	}

	@Override
	public Type visit(Not n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.e.accept(this) instanceof BooleanType) )
		{
			error.complain("Exp must be of type boolean");
		}
		return n.e.accept(this);
	}

	@Override
	public Type visit(Identifier n) {
		ErrorMsg error = new ErrorMsg();
		if(! (n.s instanceof String) )
		{
			error.complain("Identifier must be of type string");
		}
		return new IdentifierType(n.s);
	}
	
}
