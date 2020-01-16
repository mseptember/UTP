/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad1;



import java.util.*;

public class Main {
  public Main() {
    List<Integer> src1 = Arrays.asList(1, 7, 9, 11, 12);/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test1(src1));

    List<String> src2 = Arrays.asList("a", "zzzz", "vvvvvvv");/*<-- tu dopisać inicjację elementów listy */
    System.out.println(test2(src2));
  }

  public List<Integer> test1(List<Integer> src) {
    Selector sel = new Selector<Integer>() {
    	@Override
    	public boolean select(Integer i) {
    		if(i<10) {
    			return true;/*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    		}
    		else {
    			return false;
    		}
    	}
    };
    Mapper map = new Mapper<Integer, Integer>(){
    	@Override
    	public Integer map(Integer i) {
    		return i+10;/*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    	}
    };
    return   /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public List<Integer> test2(List<String> src) {
    Selector sel = new Selector<String>() {
    	@Override
    	public boolean select(String s) {
    		if(s.length()>3) {
    			return true;/*<-- definicja selektora; bez lambda-wyrażeń; nazwa zmiennej sel */
    		}
    		else {
    			return false;
    		}
    	}
    };
    Mapper map = new Mapper<String, Integer>(){
    	@Override
    	public Integer map(String s) {
    		return s.length()+10;/*<-- definicja mappera; bez lambda-wyrażeń; nazwa zmiennej map */
    	}
    };
    return   /*<-- zwrot wyniku
      uzyskanego przez wywołanie statycznej metody klasy ListCreator:
     */  ListCreator.collectFrom(src).when(sel).mapEvery(map);
  }

  public static void main(String[] args) {
    new Main();
  }
}
