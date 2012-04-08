package capitulo_1;

public class PrintStm extends Stm {

	public ExpList exps;

	public PrintStm(ExpList exps) {
		super();
		this.exps = exps;
	}

	@Override
	int maxargs() {
		this.hasPrint = true;
		exps.hasPrint = true;
		int args = exps.maxargs();
		if(args > this.numArgs) {
			this.numArgs = args;			
		}
		return this.numArgs;
	}

	@Override
	Table interpStm(Table t) {
		this.maxargs();
		IntAndTable intAndTable = exps.interpExpList(t);
		Table t1 = intAndTable.t;
		while(t1 != null) {
			System.out.print(t1.value + "  ");
			t1 = t1.tail;
		}
		System.out.println();
		return intAndTable.t;
	}
	
}
