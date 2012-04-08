package capitulo_1;

public class EseqExp extends Exp {

	public Stm stm;
	public Exp exp;
	
	public EseqExp(Stm stm, Exp exp) {
		super();
		this.stm = stm;
		this.exp = exp;
	}

	@Override
	int maxargs() {
		int args1 = 0;
		int args2 = 0;
		if(this.hasPrint == true) {
			exp.hasPrint = true;
		}
		args1 = stm.maxargs();
		args2 = exp.maxargs();
		if(this.hasPrint == true) {
			args1 = args1 + args2;
		}else {
			args1 = args1 > args2 ? args1 : args2;
		}
		return args1;
	}

	@Override
	IntAndTable interpExp(Table t) {
		this.maxargs();
		IntAndTable intAndTable; 
		t = this.stm.interpStm(t);
		intAndTable = this.exp.interpExp(t);
		if(t.id != null) {
			intAndTable.t.tail = null;
		} else {
			intAndTable.t.tail = t;
		}
		return intAndTable;
	}
		
}
