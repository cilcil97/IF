package com.licitacija.mk.service;import com.licitacija.mk.models.User;import com.licitacija.mk.models.expections.BadParametarException;import com.licitacija.mk.models.expections.NotFoundException;import com.licitacija.mk.repository.UserRepository;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class UserService {    private final UserRepository repository;    public UserService(UserRepository repository) {        this.repository = repository;    }    public User findUserById(Long id) throws NotFoundException {        return repository.findById(id).orElseThrow(() -> new NotFoundException("There is no user with this id:"+  id));    }    public List<User> getUsersByUserName(String userName) throws NotFoundException {        List<User> rez = repository.findAllByUsernameLike(userName);        if (rez != null) {            return rez;        } else {            throw new NotFoundException("There are no users with username: " + userName);        }    }    public User addNewUser(String userName,String email,String phone,String fullName,String city) throws BadParametarException {        if(phone.chars().anyMatch(Character::isAlphabetic)){            throw new BadParametarException("The number has alphabetic characters");        }        if(userName.trim()==null){            throw new BadParametarException("The username is not okay");        }        return null;        /* TODO proverka city dali postoii ss toj ime i kreiranje na novoga usera. */    }    public void deleteUser(long id) throws NotFoundException {        User user = this.findUserById(id);        repository.delete(user);    }    //TODO UPDATE na user info.}