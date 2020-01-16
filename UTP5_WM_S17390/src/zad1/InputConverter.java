package zad1;

import java.util.function.Function;

public class InputConverter <T>{

	T t;

	public InputConverter(T t){
		this.t = t;
	}

	public <T> T convertBy(Function... func) {
		Object o = t;

		for (Function f : func) {
			o =  f.apply(o);
		}

		return (T) o;
	}

}
