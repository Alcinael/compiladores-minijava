package capitulo_1;

public class Main {

	public static void main(String[] args) {

		Table t = new Table(null, 0, null);

		Stm stm = new CompoundStm(new AssignStm("a", 
									new OpExp(new NumExp(5),
												OpExp.Plus, new NumExp(3))), 
					new CompoundStm(new AssignStm("b",
						new EseqExp(new PrintStm(new PairExpList(new IdExp("a"),
							new LastExpList(new OpExp(new IdExp("a"), 
									OpExp.Minus, new NumExp(1))))), 
							new OpExp(new NumExp(10), OpExp.Times,
									new IdExp("a")))), 
						new PrintStm(new LastExpList(new IdExp("b")))));

		
/*		Stm stm = new PrintStm(new LastExpList(new OpExp(
			new EseqExp(new AssignStm("x", new NumExp(3)), new NumExp(5)), 
			OpExp.Times, 
			new EseqExp(new PrintStm(new LastExpList(new NumExp(9))), new NumExp(73)))));
*/
		
/*		Stm stm = new PrintStm( new PairExpList(new NumExp(3), 
				new PairExpList(new NumExp(5),
						new PairExpList(new NumExp(7),
								new PairExpList(new NumExp(9), new LastExpList(new NumExp(11)))))));
*/

		System.out.println("O numero de argumentos da sentenca stm eh: " + stm.maxargs());
		t = stm.interpStm(t);

	}
	
}
