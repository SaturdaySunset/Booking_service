package ru.bookingService.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, full_text, image_url;
    private Boolean enable;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFull_text(String full_text) {
        this.full_text = full_text;
    }

    public void setImage_url(String image_url) {this.image_url = image_url;}

    public void setEnable(Boolean enable) {this.enable = enable;}

    public Property(String title, String full_text, String image_url) {
        this.title = title;
        this.full_text = full_text;
        this.image_url = image_url;
    }

    public Property() {}
}
