package ru.bookingService.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Entity
@Setter
public class Property {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, full_text, image_url;
    private Boolean enable;
    
    public Property(String title, String full_text, String image_url) {
        this.title = title;
        this.full_text = full_text;
        this.image_url = image_url;
    }

    public Property() {}
}
