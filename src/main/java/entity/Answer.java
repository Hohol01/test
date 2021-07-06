package entity;

import java.io.Serializable;

public class Answer implements Serializable {

    private static final long serialVersionUID = -2353316997940324134L;

    int id;
    int induction;
    String answer;
    Boolean correct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInduction() {
        return induction;
    }

    public void setInduction(int induction) {
        this.induction = induction;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }
}
