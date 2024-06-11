package crud.data;


import org.springframework.data.jpa.repository.JpaRepository;

import crud.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
