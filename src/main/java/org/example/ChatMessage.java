package org.example;

public class ChatMessage {
    private String name;
    private String message;

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "\nChatMessage{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
