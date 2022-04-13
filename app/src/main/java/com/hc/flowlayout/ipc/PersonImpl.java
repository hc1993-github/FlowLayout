package com.hc.flowlayout.ipc;


public class PersonImpl implements PersonInterface{
    private static PersonImpl singlton =null;
    private Person person;

    private PersonImpl() {
    }
    @Override
    public Person getPerson() {
        return person;
    }
    @Override
    public void setPerson(Person person) {
        this.person = person;
    }

    public static PersonImpl getInstance() {
        if(singlton==null){
            singlton = new PersonImpl();
        }
        return singlton;
    }
}
