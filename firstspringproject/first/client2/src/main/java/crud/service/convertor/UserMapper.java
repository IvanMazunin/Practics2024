package crud.service.convertor;

import java.util.List;

import org.mapstruct.Mapper;

import crud.model.User;
import crud.service.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

	User dtoToModel(UserDto userDto);
	
	UserDto modelToDto(User user);

    List<UserDto> toListDto(List<User> users);

}
