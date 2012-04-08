package capitulo_1;

public class PairExpList extends ExpList {

	public Exp head;
	public ExpList tail;
	
	public PairExpList(Exp head, ExpList tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	@Override
	int maxargs() {
		int args1 = 0;
		int args2 = 0;
		if(this.hasPrint == true) {
			head.hasPrint = true;
			tail.hasPrint = true;
		}
		args1 = head.maxargs();
		args2 = tail.maxargs();
		if(this.hasPrint == true) {
			args1 = args1 + args2;
		}else {
			args1 = args1 > args2 ? args1 : args2;
		}
		return args1;
	}

	@Override
	IntAndTable interpExpList(Table t) {
		this.maxargs();
		Table t1 = new Table(null, 0, null);
		IntAndTable intAndTable1, intAndTable2;
		intAndTable1 = head.interpExp(t);
		intAndTable2 = tail.interpExpList(intAndTable1.t);
		if(this.hasPrint == true) {
			t1.value = intAndTable1.t.value;
			t1.tail = intAndTable2.t;
			t1.tail.value = intAndTable2.t.value;
		}
		intAndTable1.t.value = t1.value;
		intAndTable1.t.tail = t1.tail;
		intAndTable1.t.tail.value = t1.tail.value;
		return intAndTable1;
	}

}
