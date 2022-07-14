package org.java_demos.spring_core.hibernate;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    private String text;

    public Message() {
    }

    public Message(Date creationDate, String text) {
        this.id = id;
        this.creationDate = creationDate;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public String getText() {
        return text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", text='" + text + '\'' +
                '}';
    }
}
