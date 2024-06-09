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
    private Date startDate;
    private Date endDate;

    @OneToMany(mappedBy = "property")
    private List<Booking> bookings;

    public boolean isAvailable(Date startDate, Date endDate) {
        for (Booking booking : bookings) {
            if (booking.getStartDate().before(endDate) && booking.getEndDate().after(startDate)) {
                return false;
            }
        }
        return true;
    }

    public Property(String title, String full_text, String image_url) {
        this.title = title;
        this.full_text = full_text;
        this.image_url = image_url;
    }

    public Property() {}
}
//    public void setTitle(String title) {
//        this.title = title;
//    }