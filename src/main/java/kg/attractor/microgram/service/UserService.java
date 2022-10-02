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
            UserDto userDto = makeUserDto(usr);
            userDtos.add(userDto);
        }
        return userDtos;
    }
    public UserDto getUsersByEmail(String email){
        User usr = userDao.getUserByEmail(email).get(0);
        return makeUserDto(usr);
    }
    public UserDto getUsersByName(String name){
        User usr = userDao.getUserByName(name);
        return makeUserDto(usr);
    }
    public UserDto getUsersByNickName(String nickName){
        User usr = userDao.getUserByNickName(nickName);
        return makeUserDto(usr);
    }

    public Boolean isUsersByEmail(String email){
        List<User> users = userDao.getUserByEmail(email);
       if (users.size()>0){
           return true;
       }else return false;
    }

    public static UserDto makeUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setNickName(user.getNickName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}


