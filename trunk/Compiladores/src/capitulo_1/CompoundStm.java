package capitulo_1;

public class CompoundStm extends Stm {

	public Stm s1, s2;

	public CompoundStm(Stm stm1, Stm stm2) {
		super();
		this.s1 = stm1;
		this.s2 = stm2;
	}

	@Override
	int maxargs() {
		int args1 = 0;
		int args2 = 0;
		if(this.hasPrint == true) {
			s1.hasPrint = true;
			s2.hasPrint = true;
		}
		args1 = s1.maxargs();
		args2 = s2.maxargs();
		if(this.hasPrint == true) {
			args1 = args1 + args2;
		}else {
			args1 = args1 > args2 ? args1 : args2;
		}
		return args1;
	}

	@Override
	Table interpStm(Table t) {
		this.maxargs();
		t = this.s1.interpStm(t);
		t = this.s2.interpStm(t);
		return t;
	}
	
}
