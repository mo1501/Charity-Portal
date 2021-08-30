package com.example.project;

public class Charities {
    String Name,Description,Payment,Email,Type;

    public Charities(){}

    public Charities(String name, String description, String payment /*String email*/, String type) {
        Name = name;
        Description = description;
        Payment = payment;
       // Email = email;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPayment() {
        return Payment;
    }

    public void setPayment(String payment) {
        Payment = payment;
    }

    /*public String getEmail() {

        return this.getEmail();
    }

    public void setEmail(String email) {

        this.Email = email;
    }

     */

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
