package crud.first.service;

import java.util.List;

import crud.first.service.dto.UserDto;

public interface UserService {
	List<UserDto> findAll();
	UserDto findById(Long id);
	UserDto save(UserDto user);
	UserDto updateById(Long id, UserDto user);
	void deleteById(Long id);
}
