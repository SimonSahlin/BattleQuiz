/*
Ni ska skapa den apstrakta klassen Person som ska innehålla en persons namn,
        ålder och epost.
 */


public abstract class Person {
    private String name;
    private int age;
    private String eMail;

    public Person(){

    }


    public Person(String name, int age, String eMail){
        this.name = name;
        this.age = age;
        this.eMail = eMail;
    }

    //Redundant?
    public String toString(){
        return name + age + eMail;
    }


}

