package entity;

public class answer extends entity{
    int id;
    int idqution;
    String answer;
    Boolean corect;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdqution() {
        return idqution;
    }

    public void setIdqution(int idqution) {
        this.idqution = idqution;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorect() {
        return corect;
    }

    public void setCorect(Boolean corect) {
        this.corect = corect;
    }
}
