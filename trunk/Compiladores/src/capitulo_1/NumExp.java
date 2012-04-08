package capitulo_1;

public class NumExp extends Exp {

	public int num;

	public NumExp(int num) {
		super();
		this.num = num;
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
		if(this.hasPrint == true) {
			t1.value = this.num;
			t1.tail = null;
		}
		IntAndTable intAndTable = new IntAndTable(this.num, t1);
		intAndTable.t.value = this.num;
		return intAndTable;
	}
	
}
