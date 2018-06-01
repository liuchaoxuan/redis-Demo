package com.zg.kyrie.domain;

import java.io.Serializable;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class HotProduct implements Serializable{
    private Integer id;
    private String name;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
