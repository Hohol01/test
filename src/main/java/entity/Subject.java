package entity;

import java.io.Serializable;

public class Subject implements Serializable {
    private static final long serialVersionUID = -4914667202740150021L;
    int id;
    String subject;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
