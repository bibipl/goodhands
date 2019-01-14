package pl.coderslab.goodhands.gift;

import java.util.Arrays;
import java.util.List;
// Class to define regions, items etc. - fixed list of string
public class Cl {
    public String region (int pos) {
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
        if (pos < regions.size() && pos >0) return regions.get(pos);
        else return "";
    }

    public String item (int pos) {
     List<String> items = Arrays.asList(
            "ubrania, które nadają się do ponownego użycia",
            "ubrania do wyrzucenia",
            "zabawki",
            "książki",
            "inne");
     if (pos < items.size() && pos >0) return items.get(pos);
     else return "";
    }
}
