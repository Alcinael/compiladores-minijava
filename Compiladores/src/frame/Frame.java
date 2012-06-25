package frame;

import java.util.LinkedList;

import temp.Temp;
import temp.Label;
import util.BoolList;
import symbol.Symbol;

public abstract class Frame {
	abstract public Frame newFrame(Label name, util.BoolList formals);
	public Label name;
	public LinkedList<Access> formals;
	public abstract Frame newFrame(symbol name, BoolList args);
	public abstract Access allocLocal(boolean escape);
	public abstract Temp FP();
	public abstract Temp RV();
	public abstract String programTail();
}
