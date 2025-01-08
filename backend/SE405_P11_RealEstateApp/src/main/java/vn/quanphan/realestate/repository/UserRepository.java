package vn.quanphan.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.quanphan.realestate.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    List<User> findAll();

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    User findByRefreshTokenAndEmail(String token, String email);
}
