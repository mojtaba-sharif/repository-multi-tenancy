package me.ramon.repositorymultitenancy.repositories;

import me.ramon.repositorymultitenancy.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Mojtaba on 8/19/2017.
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
