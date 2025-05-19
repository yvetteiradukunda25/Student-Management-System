package org.example;

import java.sql.RowId;

public class Students {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String date_of_birth;

    public Students (int id, String first_name, String last_name, String string, String resultString) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.date_of_birth = date_of_birth;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    @Override
    public String toString() {
        return "Students [id=" + id + ", first_name=" + first_name + ", last_name="
                + last_name + ", email=" + email + ", date_of_birth=" + date_of_birth;
    }
    }




