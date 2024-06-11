package crud.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import crud.model.User;
import crud.service.convertor.UserMapper;
import crud.service.dto.UserDto;

@Component
public class UserMapperImpl implements UserMapper{

	@Override
	public User dtoToModel(UserDto userDto){
		if (userDto == null) return null;
		var user = new User();
		user.setId(userDto.getId());
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPosition(userDto.getPosition());

		return user;
	}

	@Override
	public UserDto modelToDto(User user){
		if (user == null) return null;
		var userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		userDto.setPosition(user.getPosition());

		return userDto;
	}

	@Override
	public List<UserDto> toListDto(List<User> users){
		ArrayList<UserDto> list = new ArrayList<UserDto>();
		for (User user : users) {
			list.add(modelToDto(user));
		}
		return (List<UserDto>)list;
	}

}
