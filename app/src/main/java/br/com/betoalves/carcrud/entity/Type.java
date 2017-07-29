package br.com.betoalves.carcrud.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Roberto Alves on 7/22/2017.
 */

@Entity
public class Type {

    @Id
    @Generated
    private Long id;
    private String type;

    @Generated(hash = 484206379)
    public Type(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    @Generated(hash = 1782799822)
    public Type() {
    }

    @Override
    public String toString() {
        return this.id.toString() + " - " + this.type;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
