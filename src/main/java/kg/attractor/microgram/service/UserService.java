package kg.attractor.microgram.service;

import kg.attractor.microgram.dao.UserDao;
import kg.attractor.microgram.dto.UserDto;
import kg.attractor.microgram.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public List<UserDto> getUsers(){
        List<User> users =userDao.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();
        for (User usr:users) {
            UserDto userDto = new UserDto();
            userDto.setName(usr.getName());
            userDto.setNickName(usr.getNickName());
            userDto.setEmail(usr.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }
    public UserDto getUsersByEmail(String email){
        User usr = userDao.getUserByEmail(email).get(0);
        UserDto userDto = new UserDto();
        userDto.setName(usr.getName());
        userDto.setNickName(usr.getNickName());
        userDto.setEmail(usr.getEmail());
        return userDto;
    }
    public UserDto getUsersByName(String name){
        User usr = userDao.getUserByName(name).get(0);
        UserDto userDto = new UserDto();
        userDto.setName(usr.getName());
        userDto.setNickName(usr.getNickName());
        userDto.setEmail(usr.getEmail());
        return userDto;
    }
    public UserDto getUsersByNickName(String nickName){
        User usr = userDao.getUserByName(nickName).get(0);
        UserDto userDto = new UserDto();
        userDto.setName(usr.getName());
        userDto.setNickName(usr.getNickName());
        userDto.setEmail(usr.getEmail());
        return userDto;
    }

    public Boolean isUsersByEmail(String email){
        List<User> users = userDao.getUserByEmail(email);
       if (users.size()>0){
           return true;
       }else return false;
    }
}


