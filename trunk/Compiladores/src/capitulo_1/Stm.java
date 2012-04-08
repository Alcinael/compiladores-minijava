package capitulo_1;

public abstract class Stm {

	int numArgs = 0;
	boolean hasPrint = false;
	
	abstract int maxargs();
	
	abstract Table interpStm(Table t);
	
}
