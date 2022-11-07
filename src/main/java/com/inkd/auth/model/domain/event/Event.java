package com.inkd.auth.model.domain.event;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.model.domain.customer.Customer;
import com.inkd.auth.model.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID")
    private Long eventID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTIST_ID")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @Column(name = "START_TIME", nullable = false)
    private String startTime;

    @Column(name = "END_TIME", nullable = false)
    private String endTime;

    @Column(name = "COST")
    private BigDecimal cost;

    @Lob
    @Column(name = "IMAGES")
    private byte[] images;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "TATTOO_LOCATION", nullable = false, length = 50)
    private String tattooLocation;

    @Column(name = "COMMENTS", length = 1200)
    private String comments;

    @Column(name = "CANCELLED")
    private AppsConstants.YesNo cancelled;

    @Column(name = "NO_SHOW")
    private AppsConstants.YesNo noShow;

    @Column(name = "RESCHEDULE")
    private String reschedule;

    public Long getEventID() {
        return eventID;
    }

    public void setEventID(Long eventID) {
        this.eventID = eventID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
