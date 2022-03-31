package com.example.csdict.DataModels;

public class DataModelMessage {

    String message ;
    String id;
    String sender;
    String receiver;
    String title ;
    String replies ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "DataModelMessage{" +
                "message='" + message + '\'' +
                ", id='" + id + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", title='" + title + '\'' +
                ", replies='" + replies + '\'' +
                '}';
    }

    public DataModelMessage(String message, String id, String sender, String receiver, String title) {
        this.message = message;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }
}
