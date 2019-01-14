package pl.coderslab.goodhands.gift;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PickUpData {
    private Address address;
    private LocalDate date;
    private LocalTime time;
    private String comment;

}
