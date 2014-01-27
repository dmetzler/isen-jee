package models;

import javax.persistence.Entity;

import org.junit.Before;

import play.db.jpa.Model;
import play.test.Fixtures;

@Entity
public class User extends Model {
    public String email;
    public String password;
    public String fullname;
    public boolean isAdmin;




    public User(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullname = fullName;

    }

    public static User connect(String user, String password) {
        return find("byEmailAndPassword", user, password).first();
    }



}
