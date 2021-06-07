package entity;

import java.io.Serializable;

public class test  implements Serializable {
    private static final long serialVersionUID = 5475229682929692578L;
    private int id;
    private String name;
    private String subdgect;
    private int hardnest;
    private int time;
    private int iduser;


    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubdgect() {
        return subdgect;
    }

    public void setSubdgect(String subdgect) {
        this.subdgect = subdgect;
    }

    public int getHardnest() {
        return hardnest;
    }

    public void setHardnest(int hardnest) {
        this.hardnest = hardnest;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
