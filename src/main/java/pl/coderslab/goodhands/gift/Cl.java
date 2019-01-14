package pl.coderslab.goodhands.gift;

import java.util.Arrays;
import java.util.List;
// Class to define regions, items etc. - fixed list of string
public class Cl {
    public static List<String> region () {
       List<String> regions = Arrays.asList(
        "dolnośląskie",
        "kujawsko-pomorskie",
        "lubelskie",
        "lubuskie",
        "łódzkie",
        "małopolskie",
        "mazowieckie",
        "opolskie",
        "podkarpackie",
        "podlaskie",
        "pomorskie",
        "śląskie",
        "świętokrzyskie",
        "warmińsko-mazurskie",
        "wielkopolskie",
        "zachodniopomorskie");
         return regions;

    }

    public static List<String> item () {
     List<String> items = Arrays.asList(
            "ubrania, które nadają się do ponownego użycia",
            "ubrania do wyrzucenia",
            "zabawki",
            "książki",
            "inne");
     return items;
    }
}
