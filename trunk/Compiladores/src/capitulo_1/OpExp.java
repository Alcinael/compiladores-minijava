package capitulo_1;

public class OpExp extends Exp {

	public Exp left, right;
	public int oper;
	final public static int Plus = 1, Minus = 2, Times = 3, Div = 4;
	
	public OpExp(Exp left, int oper, Exp right) {
		super();
		this.left = left;
		this.right = right;
		this.oper = oper;
	}

	@Override
	int maxargs() {
		int args1 = 0;
		int args2 = 0;
		int args3 = 0;
		args1 = left.maxargs();
		args2 = right.maxargs();
		if(this.hasPrint == true) {
			args3 = 1;
			args1 = args1 + args2 + args3;
			this.left.hasPrint = true;
			this.right.hasPrint = true;
		}else {
			args1 = args1 > args2 ? args1 : args2;
		}
		return args1;
	}
	
	@Override
	IntAndTable interpExp(Table t) {
		this.maxargs();
		Table t1 = new Table(null, 0, null);
		IntAndTable intAndTable1, intAndTable2;
		intAndTable1 = left.interpExp(t);
		intAndTable2 = right.interpExp(t);
		switch (this.oper) {
		case Plus:
			t1.value = intAndTable1.t.value + intAndTable2.t.value;
			break;
		case Minus:
			t1.value = intAndTable1.t.value - intAndTable2.t.value;
			break;
		case Times:
			t1.value = intAndTable1.t.value * intAndTable2.t.value;
			break;
		case Div:
			t1.value = intAndTable1.t.value / intAndTable2.t.value;
			break;
		default:
			break;
		}
		intAndTable1.t.value = t1.value;
		if(intAndTable1.t.tail != null && intAndTable2.t.tail != null) {
			intAndTable1.t.tail.tail = intAndTable2.t.tail;
			intAndTable1.t.tail.tail.value = intAndTable2.t.tail.value;			
		} else if(intAndTable2.t.tail != null) {
			intAndTable1.t.tail = intAndTable2.t.tail;
			intAndTable1.t.tail.value = intAndTable2.t.tail.value;
		}
		return intAndTable1;
	}

}
