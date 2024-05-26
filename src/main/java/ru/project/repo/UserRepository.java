package ru.project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
