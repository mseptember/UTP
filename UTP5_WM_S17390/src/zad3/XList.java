package zad3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class XList <T> extends ArrayList <T> {

    public XList(){
        super();
    }

    public XList(T... t){
        this();

        for (int i=0; i<t.length; i++){
            this.add(t[i]);
        }
    }

    public XList(Collection<T> col) {
        super(col);
    }

    public static <T> XList<T> of(T... t){
        return new XList<T>(t);
    }

    public static <T> XList<T> of(Collection<T> col){
        return new XList<T>(col);
    }

    public static XList<String> charsOf(String napis) {
        char[] tab = napis.toCharArray();
        List<String> list = new ArrayList<>();
        for (int i=0; i<tab.length; i++){
            list.add(Character.toString(tab[i]));
        }
        return new XList<String>(list);
    }

    public static XList<String> tokensOf(String napis, String separator) {
        String[] tab = napis.split(separator);
        return new XList<>(tab);
    }

    public static XList<String> tokensOf(String napis) {
        return tokensOf(napis, "\\s");
    }


    public XList<T> union(Collection<T> col) {
        List<T> list = new ArrayList<>(this);
        list.addAll(col);
        return new XList<T>(list);
    }


    public XList<T> union(T... t) {
        List<T> list = new ArrayList<>(this);
        Collections.addAll(list, t);
        return new XList<T>(list);
    }


    public XList<T> diff(Collection<T> col) {
        XList<T> list = new XList<T>(this);
        list.removeAll(col);
        return list;
    }

    public XList<T> unique() {
        Set<T> set = new LinkedHashSet<>(this);
        return new XList<T>(set);
    }


    /*public XList<XList<T>> combine() { // iloczyn kartezjanski (A x B x C)
        //TODO
    }*/

    public <S> XList<S> collect(Function<T, S> func){
        XList<S> list = new XList<S>();
        for (T t : this) {
            S s = func.apply(t);
            list.add(s);
        }
        return list;
    }

    public String join(String separator){
        List<T> list = new XList<T>(this);
        String s = "";
        int i = 0;

        for (T t : list) {
            if(this.size() - 1 == i) {
                s += t;
            }
            else {
                s += t + separator;
            }
            i++;
        }
        return s;
    }

    public String join(){
        return join("");
    }

    public void forEachWithIndex(BiConsumer<T,Integer> bc){
        for (int i=0; i<size(); i++){
            bc.accept(get(i), i);
        }
    }
}
