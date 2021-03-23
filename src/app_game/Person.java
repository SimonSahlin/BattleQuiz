package app_game;


import java.io.Serializable;

public abstract class Person implements Serializable {

    // ------- VARIABLES -------
    private String name;
    private int age;
    private String eMail;


    // ------- CONSTRUCTORS -------
    public Person(String name, int age, String eMail){
        this.name = name;
        this.age = age;
        this.eMail = eMail;
    }
    public Person(){}


    // ------- METHODS -------
    public String getName(){return name;}

    public String getEmail() {
        return eMail;
    }
}

