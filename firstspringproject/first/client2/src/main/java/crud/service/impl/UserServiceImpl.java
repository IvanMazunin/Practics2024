package crud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crud.data.UserRepository;
import crud.model.User;
import crud.service.UserService;
import crud.service.convertor.UserMapper;
import crud.service.dto.UserDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

	private final UserRepository userRepo;
	
	@Autowired
    private final UserMapper userMapper;

    @Override
    public List<UserDto> findAll() {
        return userMapper.toListDto(userRepo.findAll());
    }

    @Override
    public UserDto findById(Long id) {
        return Optional.of(getById(id)).map(userMapper::modelToDto).get();
    }

    @Override
    @Transactional
    public UserDto save(UserDto user) {
        return userMapper.modelToDto(userRepo.save(
                userMapper.dtoToModel(user)));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var user = getById(id);
        userRepo.delete(user);
    }

	@Override
    @Transactional
    public UserDto updateById(Long id, UserDto userDto) {
        var user = getById(id);
		if(user == null) return userMapper.modelToDto(user);
		user.setFirstName(userDto.getFirstName());
		user.setLastName(userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPosition(userDto.getPosition());
        return userMapper.modelToDto(userRepo.save(user));
    }

    private User getById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "User with id: " + id + " not found"));
    }
}
