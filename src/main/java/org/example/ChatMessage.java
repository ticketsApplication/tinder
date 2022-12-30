package org.example;

public class ChatMessage {

    private final String name;
    private final String text;

//    private final int userId;

    public ChatMessage(String name, String text) {
        this.name = name;
        this.text = text;
//        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

//    public int getUserId() {
//        return userId;
//    }


}
