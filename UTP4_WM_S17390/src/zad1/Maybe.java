package zad1;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe <T>{
    T t;

    public Maybe(T t) {
        this.t = t;
    }

    public static <T> Maybe <T> of(T t){
        return new Maybe<>(t);
    }

    public void ifPresent(Consumer<T> cons){
        if(t != null) {
            cons.accept(t);
        }
    }

    public <S> Maybe <S> map(Function<T,S> func){
        if(t != null) {
            return new Maybe<S>(func.apply(t));
        }
        else {
            return new Maybe<S>(null);
        }
    }

    public T get() throws NoSuchElementException {
        if(t != null){
            return t;
        }
        else{
            throw new NoSuchElementException("maybe is empty");
        }
    }

    public boolean isPresent(){
        if(t == null){
            return false;
        }
        else {
            return true;
        }
    }

    public T orElse(T defVal){
        if(t != null){
            return t;
        }
        else {
            return defVal;
        }
    }

    public Maybe <T> filter(Predicate<T> pred){
        if(t == null){
            return this;
        }
        else{
            if(pred.test(t)){
                return new Maybe<>(t);
            }
            else {
                return new Maybe<>(null);
            }
        }
    }

    @Override
    public String toString() {
        if(t != null){
            return "Maybe has value " + t;
        }
        else {
            return "Maybe is empty";
        }
    }
}
