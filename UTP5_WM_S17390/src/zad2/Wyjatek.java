package zad2;

import java.util.function.Function;

public interface Wyjatek<T,S> extends Function<T,S> {
    S dodajWyjatek(T t) throws Exception;

    @Override
    default S apply(T t){
        try{
            return dodajWyjatek(t);
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
