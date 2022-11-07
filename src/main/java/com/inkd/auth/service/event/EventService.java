package com.inkd.auth.service.event;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.dto.event.EventDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventService {

    EventDTO saveEvent(EventDTO eventDTO) throws AppsException;

    List<EventDTO> findAllEvents() throws AppsException;

    EventDTO updateEvent(Long eventID, EventDTO updateEventDTO) throws AppsException;

    EventDTO findByID(Long eventID) throws AppsException;
}
