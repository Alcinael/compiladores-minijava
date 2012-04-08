package capitulo_1;

public class IdExp extends Exp {

	public String id;

	public IdExp(String id) {
		super();
		this.id = id;
	}

	@Override
	int maxargs() {
		int args = 0;
		if(this.hasPrint == true) {
			args = 1;
		}
		return args;
	}

	@Override
	IntAndTable interpExp(Table t) {
		this.maxargs();
		Table t1 = new Table(null, 0, null);		
		IntAndTable intAndTable = new IntAndTable(t.lookup(this.id), t1);
		intAndTable.t.id = this.id;
		intAndTable.t.value = t.lookup(this.id);
		return intAndTable;
	}
		
}
