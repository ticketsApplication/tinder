package org.example;

import java.time.LocalDateTime;

public class Message {
    private int messageId;
    private int userFrom;
    private int userTo;
    private String message;
    private LocalDateTime dateTime;
    private String direction;

    public Message(int messageId, int userFrom, int userTo, String message, LocalDateTime dateTime, String direction) {
        this.messageId = messageId;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.message = message;
        this.dateTime = dateTime;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", message='" + message + '\'' +
                ", dateTime=" + dateTime +
                ", direction='" + direction + '\'' +
                '}';
    }
}
