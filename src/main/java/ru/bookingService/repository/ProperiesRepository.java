package ru.bookingService.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bookingService.models.Property;


public interface ProperiesRepository extends CrudRepository<Property, Long> {
}
