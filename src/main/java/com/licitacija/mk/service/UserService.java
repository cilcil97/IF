package com.licitacija.mk.service;import com.licitacija.mk.models.City;import com.licitacija.mk.models.User;import com.licitacija.mk.models.exceptions.BadParametarException;import com.licitacija.mk.models.exceptions.NotFoundException;import com.licitacija.mk.repository.UserRepository;import org.springframework.stereotype.Service;import java.util.List;@Servicepublic class UserService {    private final UserRepository repository;    private final CityService cityService;    public UserService(UserRepository repository, CityService cityService) {        this.repository = repository;        this.cityService = cityService;    }    public User findUserById(Long id) throws NotFoundException {        return repository.findById(id).orElseThrow(() -> new NotFoundException("There is no user with this id:" + id));    }    public List<User> getUsersByUserName(String userName) throws NotFoundException {        List<User> rez = repository.findAllByUsernameLike(userName);        if (rez != null) {            return rez;        } else {            throw new NotFoundException("There are no users with username: " + userName);        }    }    public void addNewUser(String userName, String email, String phone, String fullName, String cityName) throws BadParametarException {        City city = cityService.findCityByCityName(cityName);        User user = new User();        if (phone.chars().anyMatch(Character::isAlphabetic)) {            throw new BadParametarException("The number has alphabetic characters");        }        List<User> allUsers = repository.findAll();        if (userName == null || userName.equals("") || allUsers.stream().anyMatch(i -> i.getUsername().equals(userName))) {            throw new BadParametarException("The username is not okay or it is already taken!");        }        user.setUsername(userName);        user.setEmail(email);        user.setPhone(phone);        user.setName(fullName);        user.setCity(city);        repository.save(user);    }    public void deleteUser(long id) throws NotFoundException {        User user = this.findUserById(id);        repository.delete(user);    }    //TODO UPDATE na user info.    public void updateUser(long userId, String userName, String email, String phone, String fullName, String cityName) {        User user = findUserById(userId);        if (userName != null) {            user.setUsername(userName);        }        if (email != null) {            user.setEmail(email);        }        if (phone != null) {            if (phone.chars().anyMatch(Character::isAlphabetic)) {                throw new BadParametarException("The number has alphabetic characters");            } else {                user.setPhone(phone);            }        }        if (cityName != null) {            City city = cityService.findCityByCityName(cityName);            user.setCity(city);        }        if (fullName != null) {            user.setName(fullName);        }        repository.save(user);    }}