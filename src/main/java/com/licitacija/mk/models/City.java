package com.licitacija.mk.models;import com.fasterxml.jackson.annotation.JsonIgnore;import javax.persistence.*;import java.util.ArrayList;import java.util.List;@Entitypublic class City {    @Id    @GeneratedValue(strategy = GenerationType.SEQUENCE)    private Long city_id;    private String cityName;    @OneToMany(mappedBy = "city")    @JsonIgnore    private List<User> users;    @OneToMany(mappedBy = "city")    @JsonIgnore    private List<Product> products;    public City(){}    public City(String cityName) {        this.cityName = cityName;    }    public Long getCity_id() {        return city_id;    }    public void setCity_id(Long city_id) {        this.city_id = city_id;    }    public String getCityName() {        return cityName;    }    public void setCityName(String cityName) {        this.cityName = cityName;    }    public List<User> getUsers() {        if(this.users==null){            users=new ArrayList<>();        }        return users;    }    public void setUsers(List<User> users) {        this.users = users;    }    public List<Product> getProducts() {        if(this.products==null){            this.products=new ArrayList<>();        }        return products;    }    public void setProducts(List<Product> products) {        this.products = products;    }}