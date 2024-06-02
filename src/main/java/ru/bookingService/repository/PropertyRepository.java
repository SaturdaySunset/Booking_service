package ru.bookingService.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.bookingService.models.Property;


public interface PropertyRepository extends CrudRepository<Property, Long> {
    @Modifying
    @Query("DELETE FROM Property p WHERE p.id = :id")
    void removeProperty(@Param("id") Long id);
}
