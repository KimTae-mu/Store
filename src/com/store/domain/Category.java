package com.store.domain;

import java.io.Serializable;

/**
 * Created by Taeyeon-Serenity on 2017/7/25.
 */
public class Category implements Serializable {
    private String cid;
    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
