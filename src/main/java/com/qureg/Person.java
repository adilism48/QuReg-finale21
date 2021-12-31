package com.qureg;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final IntegerProperty card_id = new SimpleIntegerProperty(this, "card_id");
    private final StringProperty firstname = new SimpleStringProperty(this, "firstname");
    private final StringProperty surname = new SimpleStringProperty(this, "surname");
    private final StringProperty info = new SimpleStringProperty(this, "info");

    public Integer id() {
        return id.get();
    }

    public void setId(Integer id) {
        this.id.set(id);
    }

    public Integer getCard_id() {
        return card_id.get();
    }

    public void setCard_id(Integer card_id) {
        this.card_id.set(card_id);
    }

    public String getFirstname() {
        return firstname.get();
    }

    public void setFirstname(String firstname) {
        this.firstname.set(firstname);
    }

    public String getSurname() {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getInfo() {
        return info.get();
    }

    public void setInfo(String info) {
        this.info.set(info);
    }

    public Person(Integer id, Integer card_id, String firstname, String surname, String info) {
        setId(id);
        setCard_id(card_id);
        setFirstname(firstname);
        setSurname(surname);
        setInfo(info);
    }

    @Override
    public String toString() {
        return id.get() + "|" + " " + card_id.get() + " " + firstname.get() + " " + surname.get() + " " + "(" + info.get() + ")";
    }
}
