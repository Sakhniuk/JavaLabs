package org.example;

import com.google.gson.Gson;

public class PersonJsonDemo {
    public static void main(String[] args) {
        Person original = new Person("Шевченко", "Тарас", 47);

        Gson gson = new Gson();


        String json = gson.toJson(original);
        System.out.println("JSON: " + json);


        Person restored = gson.fromJson(json, Person.class);
        System.out.println("Restored: " + restored);

        System.out.println("equals: " + original.equals(restored));
    }
}
