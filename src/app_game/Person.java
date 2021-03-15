package app_game;

public abstract class Person {

    // --- VARIABLES ---
    private String name;
    private int age;
    private String eMail;

    // --- CONSTRUCTORS ---
    public Person(){

    }
    public Person(String name, int age, String eMail){
        this.name = name;
        this.age = age;
        this.eMail = eMail;
    }

    // --- METHODS ---
    public String personInformationToString(){
        return name + age + eMail;
    }

    // --- Getters & Setters ---
    public String getName(){return name;}
}

