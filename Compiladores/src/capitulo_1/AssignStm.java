package capitulo_1;

public class AssignStm extends Stm {

	public String id;
	public Exp exp;
	
	public AssignStm(String id, Exp exp) {
		super();
		this.id = id;
		this.exp = exp;
	}

	@Override
	int maxargs() {
		int args1 = 0;
		int args2 = 0;
		if(this.hasPrint == true) {
			args1 = 1;
			exp.hasPrint = true;
		}
		args2 = exp.maxargs();
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
		t = t.update(this.id, this.exp.interpExp(t).t.value);
		return t;
	}
		
}
