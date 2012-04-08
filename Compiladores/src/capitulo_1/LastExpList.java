package capitulo_1;

public class LastExpList extends ExpList {

	public Exp head;

	public LastExpList(Exp head) {
		super();
		this.head = head;
	}

	@Override
	int maxargs() {
		if(this.hasPrint == true) {
			head.hasPrint = true;
		}
		return head.maxargs();
	}

	@Override
	IntAndTable interpExpList(Table t) {
		this.maxargs();
		Table t1 = new Table(null, 0, null);
		IntAndTable intAndTable;
		intAndTable = this.head.interpExp(t);
		t1.value = intAndTable.t.value;
		return intAndTable;
	}
		
}
