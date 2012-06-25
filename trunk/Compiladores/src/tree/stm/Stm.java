package tree.stm;
abstract public class Stm {
	abstract public ExpList kids();
	abstract public Stm build(ExpList kids);
}

