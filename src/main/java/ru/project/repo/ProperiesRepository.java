package ru.project.repo;

import org.springframework.data.repository.CrudRepository;
import ru.project.models.Property;

public interface ProperiesRepository extends CrudRepository<Property, Long> {
}
