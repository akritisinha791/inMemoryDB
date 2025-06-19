package org.inMemoryDB;
//data needs to be stored in a class that implements Record interface
public class User implements Record{
    private Long id;
    private String name;
    private String email;
    private int age;
    //overide from interface Record
    @Override
    public Long getId(){
        return id;
    }
    @Override
    public void setId(Long id){
        this.id=id;
    }

    //Constructors
    public User(){}

    public User(String name, String email, int age){
        this.name=name;
        this.email=email;
        this.age=age;
    }

    //Getters and Setters
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age=age;
    }

    //Override class methods
    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }

}
