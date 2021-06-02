package servlet;

import db.DAOlogin;

public class sql {
    public static void main(String[] args) {


        DAOlogin db=new DAOlogin();
        System.out.println(db.get_pass_by_login("admin"));
    }
}
