package ru.bookingService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.bookingService.entities.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Modifying
    @Query("DELETE FROM Property p WHERE p.id = :id")
    void removeProperty(@Param("id") Long id);
}

