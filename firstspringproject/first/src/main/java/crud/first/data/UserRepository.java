package crud.first.data;

import org.springframework.data.repository.CrudRepository;

import crud.first.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
