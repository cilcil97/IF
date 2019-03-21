package com.licitacija.mk.models;import com.fasterxml.jackson.annotation.JsonIgnore;import javax.persistence.*;import java.util.ArrayList;import java.util.List;@Entity()@Table(name = "\"User\"")public class User {    //flyway dependencies treba da dodademo u resources i flyway kao dependencie u gradle    @Id    @GeneratedValue(strategy = GenerationType.SEQUENCE)    private long user_id;    @ManyToOne    @JoinColumn( name = "city_id")    private City city;    private String username;    private String email;    private String full_name;    private String phone;    public void setUser_id(int user_id) {        this.user_id = user_id;    }    @OneToMany(mappedBy = "givenByUser",cascade = CascadeType.REMOVE, orphanRemoval = true)    @JsonIgnore    private List<Offer> offers;    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)    private List<Product> userProducts;    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE, orphanRemoval = true)    @JsonIgnore    private List<Interest> interests;    public User(){}    public User(City city, String username, String email, String full_name, String phone) {        this.city = city;        this.username = username;        this.email = email;        this.full_name = full_name;        this.phone = phone;    }    public long getUser_id() {        return user_id;    }    public City getCity() {        return city;    }    public void setCity(City city) {        this.city = city;    }    public String getUsername() {        return username;    }    public void setUsername(String username) {        this.username = username;    }    public String getEmail() {        return email;    }    public void setEmail(String email) {        this.email = email;    }    public String getFull_name() {        return full_name;    }    public void setFull_name(String full_name) {        this.full_name = full_name;    }    public String getPhone() {        return phone;    }    public void setPhone(String phone) {        this.phone = phone;    }    public List<Offer> getOffers() {        if(this.offers==null){            this.offers=new ArrayList<>();        }        return offers;    }    public void setOffers(List<Offer> offers) {        this.offers = offers;    }}