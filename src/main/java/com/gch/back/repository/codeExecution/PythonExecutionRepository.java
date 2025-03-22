package com.gch.back.repository.codeExecution;

import com.gch.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PythonExecutionRepository extends JpaRepository<User, Long> {
}
