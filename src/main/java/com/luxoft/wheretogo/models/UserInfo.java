package com.luxoft.wheretogo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

/**
 * Created by sasha on 19.07.16.
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"name", "email"})
@ToString(of = {"name", "email"})
public class UserInfo {
    private long id;
    private String role;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String picture;
    private String description;
    private String phoneNumber;
    private boolean active;
    private Set<Category> interestingCategories;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yy", timezone="default")
    private Date birthday;

    public UserInfo(String role, String email, String firstName, String lastName, boolean active, String picture, long id, String description, String phone, Date birthday, Set<Category> interestingCategories) {
        this.id=id;
        this.role = role;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
        if(!picture.equals("")){
            this.picture = "userImage";
        }else{
            this.picture = picture;
        }
        this.description=description;
        this.phoneNumber=phone;
        this.birthday=birthday;
        this.interestingCategories = interestingCategories;
    }

    public UserInfo() {}
}
