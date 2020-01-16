/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;

import java.util.ArrayList;
import java.util.List;

public class ListCreator<T, S> {

    public List<T> list;

    public static <T, S> ListCreator<T, S> collectFrom(List<T> l) {
        ListCreator<T, S> lc = new ListCreator<T, S>();
        lc.list = l;
        return lc;
    }

    ListCreator<T, S> when(Selector<T> selector) { //wybiera te elementy listy które spełniają warunek
        List<T> listaWynikow = new ArrayList<>();
        for (T t : this.list) {
            if (selector.select(t)) {
                listaWynikow.add(t);
            }
        }
        this.list = listaWynikow;
        return this;
    }

    List<S> mapEvery(Mapper<T, S> mapper) { //robi "coś" (polecenie) z tymi elementami listy które spełniły warunek
        List<S> listaWynikow = new ArrayList<>();
        for (T t : this.list) {
            listaWynikow.add(mapper.map(t));
        }
        return listaWynikow;
    }
}
