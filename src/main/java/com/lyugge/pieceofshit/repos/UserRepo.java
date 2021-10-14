package com.lyugge.pieceofshit.repos;

import com.lyugge.pieceofshit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
