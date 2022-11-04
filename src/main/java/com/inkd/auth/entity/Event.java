package com.inkd.auth.entity;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @Column(name="cost")
    private float cost;

    @Column(name="tattoo_location", nullable = false, length = 50)
    private String tattooLocation;

    @Column(name="comments", length = 1200)
    private String comments;

    @Column(name="cancelled")
    private boolean cancelled;

    @Column(name="no_show")
    private boolean noShow;



    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isNoShow() {
        return noShow;
    }

    public void setNoShow(boolean noShow) {
        this.noShow = noShow;
    }
}
