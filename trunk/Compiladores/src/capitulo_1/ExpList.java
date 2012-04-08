package capitulo_1;

public abstract class ExpList {

	boolean hasPrint = false;
	
	abstract int maxargs();
	
	abstract IntAndTable interpExpList(Table t);
	
}
