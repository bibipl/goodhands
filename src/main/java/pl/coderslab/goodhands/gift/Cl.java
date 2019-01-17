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

    public static List<String> clothesForWhom () {
        List<String> forWhom = Arrays.asList(
                "Męskie",
                "Damskie",
                "Dziecięce dla dziewczynki",
                "Dziecięce dla chłopca"
                );
        return forWhom;
    }

    public static List<String> clothesForWhat () {
        List<String> forWhat = Arrays.asList(
                "Sezon Jesień-Zima",
                "Sezon Wiosna-Lato"
        );
        return forWhat;
    }

    public static List<String> childrenAge (){
        List <String> age = Arrays.asList(
                "0-2",
                "3-5",
                "6-8",
                "9-12",
                "12-15",
                "15+"
        );
        return age;
    }

    public static List<String> numberOfBags (){
        List <String> bags = Arrays.asList(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                ">10"
        );
        return bags;
    }

    public static List<String> childrenGender (){
        List <String> gen = Arrays.asList(
                "chłopiec",
                "dziewczynka"
        );
        return gen;
    }

    public static List<String> booksForWhom (){
        List <String> forWhom = Arrays.asList(
                "Dla Dorosłych",
                "Dla Dzieci",
                "Dla młodzieży",
                "Edukacyjne"
        );
        return forWhom;
    }
}
