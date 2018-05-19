package com.example.biao.greendaodemo;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Uaer {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 294595025)
    public Uaer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1579486460)
    public Uaer() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
