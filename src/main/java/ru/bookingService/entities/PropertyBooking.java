package ru.bookingService.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PropertyBooking {
    private Date startDate;
    private Date endDate;

//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
}