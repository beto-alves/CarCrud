package br.com.betoalves.carcrud.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Roberto Alves on 7/22/2017.
 */

@Entity
public class Brand {

    @Id
    @Generated
    private Long id;
    private String brand;

    @Generated(hash = 2068433194)
    public Brand(Long id, String brand) {
        this.id = id;
        this.brand = brand;
    }

    @Generated(hash = 128156227)
    public Brand() {
    }

    @Override
    public String toString() {
        return this.id.toString() + " - " + this.brand;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
