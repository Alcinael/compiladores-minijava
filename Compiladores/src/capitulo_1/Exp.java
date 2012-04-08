package capitulo_1;

public abstract class Exp {

	boolean hasPrint = false;
	
	abstract int maxargs();
	
	abstract IntAndTable interpExp(Table t);
	
}
