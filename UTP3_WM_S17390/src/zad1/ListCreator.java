package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class ListCreator<T, S> {

    private List<T> list;

    public static <T, S> ListCreator<T, S> collectFrom(List<T> l){
        ListCreator<T,S> lc = new ListCreator<T, S>();
        lc.list = l;
        return lc;
    }

    public ListCreator<T,S> when (Predicate<T> predicate){
        List<T> listaWynikow = new ArrayList<>();
        for (T t : this.list) {
            if (predicate.test(t)) {
                listaWynikow.add(t);
            }
        }
        this.list = listaWynikow;
        return this;
    }

    public <S> List<S> mapEvery(Function<T, S> function){
        List<S> listaWynikow = new ArrayList<>();
        for (T t : this.list) {
            listaWynikow.add(function.apply(t));
        }
        return listaWynikow;
    }
}