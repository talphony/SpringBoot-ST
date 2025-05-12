package com.example.KR.repositories;
import com.example.KR.models.Project;
import com.example.KR.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByMembers_User(User user);
}