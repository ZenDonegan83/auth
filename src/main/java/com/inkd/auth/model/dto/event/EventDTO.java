package com.inkd.auth.model.dto.event;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.model.domain.customer.Customer;
import com.inkd.auth.model.domain.event.Event;
import com.inkd.auth.model.domain.user.User;
import com.inkd.auth.model.dto.customer.CustomerDTO;
import com.inkd.auth.model.dto.user.UserDTO;
import com.inkd.auth.utils.CalendarUtils;

import java.io.Serializable;
import java.math.BigDecimal;

public class EventDTO implements Serializable {

    private static final long serialVersionUID = -1726117723906311822L;

    private Long eventID;

    private Long artistID;

    private UserDTO userDTO;

    private Long customerID;

    private CustomerDTO customerDTO;

    private String startTime;

    private String endTime;

    private BigDecimal cost;

    private byte[] images;

    private String startDateStr;

    private String endDateStr;

    private String tattooLocation;

    private String comments;

    private AppsConstants.YesNo cancelled;

    private AppsConstants.YesNo noShow;

    private String reschedule;

    public EventDTO() {
    }

    public EventDTO(Event event) {
        this.eventID = event.getEventID();

        User user = event.getUser();
        this.artistID = user.getArtistID();
        this.userDTO = new UserDTO(user);

        Customer customer = event.getCustomer();
        this.customerID = customer.getCustomerID();
        this.customerDTO = new CustomerDTO(customer);

        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.cost = event.getCost();
        this.images = event.getImages();
        this.startDateStr = CalendarUtils.getDefaultFormattedDate(event.getStartDate());
        this.endDateStr = CalendarUtils.getDefaultFormattedDate(event.getEndDate());
        this.tattooLocation = event.getTattooLocation();
        this.comments = event.getComments();
        this.cancelled = event.getCancelled();
        this.noShow = event.getNoShow();
        this.reschedule = event.getReschedule();
    }

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public Long getArtistID() {
        return artistID;
    }

    public void setArtistID(Long artistID) {
        this.artistID = artistID;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public byte[] getImages() {
        return images;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }

    public String getTattooLocation() {
        return tattooLocation;
    }

    public void setTattooLocation(String tattooLocation) {
        this.tattooLocation = tattooLocation;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public AppsConstants.YesNo getCancelled() {
        return cancelled;
    }

    public void setCancelled(AppsConstants.YesNo cancelled) {
        this.cancelled = cancelled;
    }

    public AppsConstants.YesNo getNoShow() {
        return noShow;
    }

    public void setNoShow(AppsConstants.YesNo noShow) {
        this.noShow = noShow;
    }

    public String getReschedule() {
        return reschedule;
    }

    public void setReschedule(String reschedule) {
        this.reschedule = reschedule;
    }
}
