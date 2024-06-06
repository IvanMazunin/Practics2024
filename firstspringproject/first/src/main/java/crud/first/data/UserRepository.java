package crud.first.data;


import org.springframework.data.jpa.repository.JpaRepository;

import crud.first.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
