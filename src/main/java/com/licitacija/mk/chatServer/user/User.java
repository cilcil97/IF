//package com.licitacija.mk.chatServer.user;
//
//import java.io.Serializable;
//import java.util.Objects;
//import java.util.UUID;
//
//public class User implements Serializable {
//    private String id;
//    private String name;
//
//    public User() {
//    }
//
//    public User(String name) {
//        this.name = name;
//        id = UUID.randomUUID().toString();
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getId() {
//        return this.id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name);
//    }
//
//
//}
