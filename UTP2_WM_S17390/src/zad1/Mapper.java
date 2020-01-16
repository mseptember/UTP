/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;

public interface Mapper<T, S> {
    S map(T t);
}
