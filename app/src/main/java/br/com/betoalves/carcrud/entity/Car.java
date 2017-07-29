package br.com.betoalves.carcrud.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Roberto on 7/22/2017.
 */

@Entity
public class Car {

    @Id
    @Generated
    private Long id;
    private String name;
    private double price;
    private double weight;

    private Long brandId;
    private Long typeId;

    @ToOne(joinProperty = "brandId")
    private Brand brand;
    @ToOne(joinProperty = "typeId")
    private Type type;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 709963916)
    private transient CarDao myDao;
    @Generated(hash = 1871207717)
    public Car(Long id, String name, double price, double weight, Long brandId,
            Long typeId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.brandId = brandId;
        this.typeId = typeId;
    }
    @Generated(hash = 625572433)
    public Car() {
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
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public double getWeight() {
        return this.weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public Long getBrandId() {
        return this.brandId;
    }
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    public Long getTypeId() {
        return this.typeId;
    }
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    @Generated(hash = 676862821)
    private transient Long brand__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 643520319)
    public Brand getBrand() {
        Long __key = this.brandId;
        if (brand__resolvedKey == null || !brand__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BrandDao targetDao = daoSession.getBrandDao();
            Brand brandNew = targetDao.load(__key);
            synchronized (this) {
                brand = brandNew;
                brand__resolvedKey = __key;
            }
        }
        return brand;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1524325631)
    public void setBrand(Brand brand) {
        synchronized (this) {
            this.brand = brand;
            brandId = brand == null ? null : brand.getId();
            brand__resolvedKey = brandId;
        }
    }
    @Generated(hash = 506996655)
    private transient Long type__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 344440077)
    public Type getType() {
        Long __key = this.typeId;
        if (type__resolvedKey == null || !type__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TypeDao targetDao = daoSession.getTypeDao();
            Type typeNew = targetDao.load(__key);
            synchronized (this) {
                type = typeNew;
                type__resolvedKey = __key;
            }
        }
        return type;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 156822331)
    public void setType(Type type) {
        synchronized (this) {
            this.type = type;
            typeId = type == null ? null : type.getId();
            type__resolvedKey = typeId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 679603784)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCarDao() : null;
    }

}
