/**
 *
 *  @author Wrzesień Maciej S17390
 *
 */

package zad3;


import java.util.*;

// Plik Main.java może być dowolnie modyfikowany,
// ale punkty uzyskuje się za właściwe dzialanie poszczególnych pokazanych tu metod klasy XList.

// Jeżeli nie oprogramujemy wszystkich metod, to z klasy Main nalezy usunąć te fragmenty,
// które powodują błędy w kompilacji - w przeciwnym razie nie uzyskamy punktów.

public class Main {
  public static void main(String[] args) {
    // Pewne dodatkowe zestawy danych
    Integer[] ints = { 100, 200, 300 };
    Set<Integer> set = new HashSet<>(Arrays.asList(3, 4, 5));

    // Sposoby tworzenia
    XList<Integer> list1 = new XList<>(1, 3, 9, 11);
    XList<Integer> list2 = XList.of(5, 6, 9);
    XList<Integer> list3 = new XList(ints);
    XList<Integer> list4 = XList.of(ints);
    XList<Integer> list5 = new XList(set);
    XList<Integer> list6 = XList.of(set);

    System.out.println(list1); // [1, 3, 9, 11]
    System.out.println(list2); // [5, 6, 9]
    System.out.println(list3); // [100, 200, 300]
    System.out.println(list4); // [100, 200, 300]
    System.out.println(list5); // [3, 4, 5]
    System.out.println(list6); // [3, 4, 5]

    // --- i pomocnicze metody do tworzenia z napisów
    XList<String> slist1 = XList.charsOf("ala ma kota");
    XList<String> slist2 = XList.tokensOf("ala ma kota");
    XList<String> slist3 = XList.tokensOf("A-B-C", "-");

    System.out.println(slist1); // [a, l, a,  , m, a,  , k, o, t, a]
    System.out.println(slist2); // [ala, ma, kota]
    System.out.println(slist3); // [A, B, C]

    // Metoda union - suma elementów (zwraca  nową x-list z dołączaną do tej x-list  zawartością kolekcji)
    List<Integer> m1 = list1.union(list2);  // oczywiście, można podstawiać na List
    System.out.println(m1); // [1, 3, 9, 11, 5, 6, 9]
    // można wykonywać wszystkie operacje z interfejsu List, np:
    m1.add(11);
    System.out.println(m1); // [1, 3, 9, 11, 5, 6, 9, 11]
    XList<Integer> m2 = (XList<Integer>) m1;
    XList<Integer> m3 = m2.union(ints).union(XList.of(4, 4));
    System.out.println(m2); // m2 się nie zmienia - [1, 3, 9, 11, 5, 6, 9, 11]
    System.out.println(m3); // wynik jest w m3 - [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4]
    m3 = m3.union(set);
    System.out.println(m3); // [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5]

    // Widzieliśmy metode union
    // Teraz metoda diff(dowolna kolekcja) (zwraca x-list zawierającą te elementy tej x-list, które nie występują w kolekcji)
    System.out.println(m3.diff(set));  // wszystko z m3, co nie jest w set - [1, 9, 11, 6, 9, 11, 100, 200, 300]
    System.out.println(XList.of(set).diff(m3)); // co jest w set, czego nie ma w m3 - []

    // Metoda unique - zwraca nową Xlist bez duplikatow
    XList<Integer> uniq = m3.unique(); // lista, nie Set
    System.out.println(uniq); // [1, 3, 9, 11, 5, 6, 100, 200, 300, 4]

    // kombinacje (kolejność jest istotna) - zwraca x-listę list-kombinacji elementów z poszczególnych kolekcji, będących elementami tej x-listy
    List<String> sa = Arrays.asList( "a", "b");
    List<String> sb = Arrays.asList( "X", "Y", "Z" );
    XList<String> sc = XList.charsOf( "12" );
    XList toCombine = XList.of(sa, sb, sc);
    System.out.println(toCombine); // [[a, b], [X, Y, Z], [1, 2]]
    //XList<XList<String>> cres = toCombine.combine();
    //System.out.println(cres); // [[a, X, 1], [b, X, 1], [a, Y, 1], [b, Y, 1], [a, Z, 1], [b, Z, 1], [a, X, 2], [b, X, 2], [a, Y, 2], [b, Y, 2], [a, Z, 2], [b, Z, 2]]

    // collect i join
      // collect - zwraca nową x-listę, której elemenatmi są wyniki funkcji stosowanej wobec elementów tej x-listy
      // join - zwraca napis, będący połączeniem elementów tej x-listy, z ewentualnie wstawionym pomiędzy nie separatorem
      XList<XList<String>> cres = new XList<>();
    XList<String> j1 = cres.collect( list -> list.join());
    System.out.println(j1.join(" ")); // aX1 bX1 aY1 bY1 aZ1 bZ1 aX2 bX2 aY2 bY2 aZ2 bZ2
    XList<String> j2 =cres.collect( list -> list.join("-"));
    System.out.println(j2.join(" ")); // a-X-1 b-X-1 a-Y-1 b-Y-1 a-Z-1 b-Z-1 a-X-2 b-X-2 a-Y-2 b-Y-2 a-Z-2 b-Z-2

    // forEachWithIndex - do iterowania po liście z dostępem i do elementów i do ich indeksów
    XList<Integer> lmod = XList.of(1,2,8, 10, 11, 30, 3, 4);
    lmod.forEachWithIndex( (e, i) -> lmod.set(i, e*2));
    System.out.println(lmod); // [2, 4, 16, 20, 22, 60, 6, 8]
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(e); } );
    System.out.println(lmod); //[4, 16, 22, 60, 8]
    lmod.forEachWithIndex( (e, i) -> { if (i % 2 == 0) lmod.remove(i); } );
    System.out.println(lmod); // Pytanie: dlaczego mamy taki efekt? // [16, 22, 60, 8]
    //Ponieważ remove(e) usuwa pierwszy NAPOTKANY (odpowiadający zadanemy warunkowie) element listy
    // a remove(i) usuwa pierwszy element listy (element o indexie 1)

  }
}

    /*
    Ten program wypisuje na konsoli:

        [1, 3, 9, 11]
        [5, 6, 9]
        [100, 200, 300]
        [100, 200, 300]
        [3, 4, 5]
        [3, 4, 5]
        [a, l, a,  , m, a,  , k, o, t, a]
        [ala, ma, kota]
        [A, B, C]
        [1, 3, 9, 11, 5, 6, 9]
        [1, 3, 9, 11, 5, 6, 9, 11]
        [1, 3, 9, 11, 5, 6, 9, 11]
        [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4]
        [1, 3, 9, 11, 5, 6, 9, 11, 100, 200, 300, 4, 4, 3, 4, 5]
        [1, 9, 11, 6, 9, 11, 100, 200, 300]
        []
        [1, 3, 9, 11, 5, 6, 100, 200, 300, 4]
        [[a, b], [X, Y, Z], [1, 2]]
        [[a, X, 1], [b, X, 1], [a, Y, 1], [b, Y, 1], [a, Z, 1], [b, Z, 1], [a, X, 2], [b, X, 2], [a, Y, 2], [b, Y, 2], [a, Z, 2], [b, Z, 2]]
        aX1 bX1 aY1 bY1 aZ1 bZ1 aX2 bX2 aY2 bY2 aZ2 bZ2
        a-X-1 b-X-1 a-Y-1 b-Y-1 a-Z-1 b-Z-1 a-X-2 b-X-2 a-Y-2 b-Y-2 a-Z-2 b-Z-2
        [2, 4, 16, 20, 22, 60, 6, 8]
        [4, 16, 22, 60, 8]
        [16, 22, 60, 8]
        */