package app_game;
/*
Ni ska skapa den apstrakta klassen app_game.Person som ska innehålla en persons namn,
        ålder och epost.
 */


import java.io.Serializable;

public abstract class Person implements Serializable {
    private String name;
    private int age;
    private String eMail;




    public Person(String name, int age, String eMail){
        this.name = name;
        this.age = age;
        this.eMail = eMail;
    }
    public Person(){}

    public String getName(){return name;}

    public String geteMail() {
        return eMail;
    }
}

