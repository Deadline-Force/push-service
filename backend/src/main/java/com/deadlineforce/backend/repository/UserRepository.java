package com.deadlineforce.backend.repository;

import com.deadlineforce.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
    @RestResource(exported = false)
    Page<User> findByUsernameContaining(String username, Pageable pageable);
}
