package com.inkd.auth.entity;

import javax.persistence.*;

@Entity
@Table(name="event")
public class Event {

    private Long id;
    private float cost;
    private String tattooLocation;
    private String comments;
    private boolean cancelled;
    private boolean noShow;

}
