package edu.neu.numad22sp_jovianebellegarde;

public class Website {
    private final String name;
    private final String link;

    public Website(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public String getName() {
        return this.name;
    }

    public String getLink() {
        return this.link;
    }
}
