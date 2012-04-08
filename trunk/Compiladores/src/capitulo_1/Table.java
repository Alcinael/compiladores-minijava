package capitulo_1;

public class Table {

	String id;
	int value;
	Table tail;
	
	public Table(String id, int value, Table tail) {
		super();
		this.id = id;
		this.value = value;
		this.tail = tail;
	}
	
	Table update(String id, int value) {
		Table t1 = new Table(id, value, this);
		t1.tail = this;
		return t1;
	}
	
	int lookup(String key) {
		int resultado = -1;
		Table t1 = this;
		while(t1.id != null && !t1.id.equals(key)) {
			t1 = t1.tail;
		}
		if(t1.id == key) {
			resultado = t1.value;
		}
		return resultado;
	}
	
}
