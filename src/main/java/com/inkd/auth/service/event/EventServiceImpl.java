package com.inkd.auth.service.event;

import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.domain.event.Event;
import com.inkd.auth.model.dto.event.EventDTO;
import com.inkd.auth.repository.event.EventRepository;
import com.inkd.auth.service.customer.CustomerService;
import com.inkd.auth.service.user.UserService;
import com.inkd.auth.utils.CalendarUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Override
    public EventDTO saveEvent(EventDTO eventDTO) throws AppsException {

        //Validate Event DTO
        this.validateEventDTO(eventDTO, true);

        Event event = new Event();

        event.setUser(userService.findUserByID(eventDTO.getArtistID()));
        event.setCustomer(customerService.findCustomerByID(eventDTO.getCustomerID()));
        event.setStartTime(eventDTO.getStartTime());
        event.setEndTime(eventDTO.getEndTime());
        event.setCost(eventDTO.getCost());
        event.setImages(eventDTO.getImages());
        event.setStartDate(CalendarUtils.getDefaultParsedDateOnly(eventDTO.getStartDateStr()));
        event.setEndDate(CalendarUtils.getDefaultParsedDateOnly(eventDTO.getEndDateStr()));
        event.setTattooLocation(eventDTO.getTattooLocation());
        event.setComments(eventDTO.getComments());
        event.setCancelled(eventDTO.getCancelled());
        event.setNoShow(eventDTO.getNoShow());
        event.setReschedule(eventDTO.getReschedule());

        event = eventRepository.saveAndFlush(event);

        return new EventDTO(event);
    }

    @Override
    public List<EventDTO> findAllEvents() throws AppsException {
        List<Event> eventList = eventRepository.findAll();
        List<EventDTO> eventDTOList = new ArrayList<>();

        for (Event event : eventList) {
            eventDTOList.add(new EventDTO(event));
        }

        return eventDTOList;
    }

    @Override
    public EventDTO updateEvent(Long eventID, EventDTO updateEventDTO) throws AppsException {
        if (eventID == null) {
            throw new AppsException("Event ID is not valid");
        }

        if (!this.eventRepository.existsById(eventID)) {
            throw new AppsException("Event is not found");
        } else {
            Event event = eventRepository.getById(eventID);

            //Validate Customer DTO
            this.validateEventDTO(updateEventDTO, false);

            event.setUser(userService.findUserByID(updateEventDTO.getArtistID()));
            event.setCustomer(customerService.findCustomerByID(updateEventDTO.getCustomerID()));
            event.setStartTime(updateEventDTO.getStartTime());
            event.setEndTime(updateEventDTO.getEndTime());
            event.setCost(updateEventDTO.getCost());
            event.setImages(updateEventDTO.getImages());
            event.setStartDate(CalendarUtils.getDefaultParsedDateOnly(updateEventDTO.getStartDateStr()));
            event.setEndDate(CalendarUtils.getDefaultParsedDateOnly(updateEventDTO.getEndDateStr()));
            event.setTattooLocation(updateEventDTO.getTattooLocation());
            event.setComments(updateEventDTO.getComments());
            event.setCancelled(updateEventDTO.getCancelled());
            event.setNoShow(updateEventDTO.getNoShow());
            event.setReschedule(updateEventDTO.getReschedule());

            event = eventRepository.saveAndFlush(event);

            return new EventDTO(event);
        }
    }

    @Override
    public EventDTO findByID(Long eventID) throws AppsException {
        if (eventID == null) {
            throw new AppsException("Event ID is not valid");
        }

        if (!eventRepository.existsById(eventID)) {
            throw new AppsException("Event is not found");
        } else {
            Event event = eventRepository.getById(eventID);

            return new EventDTO(event);
        }
    }

    private void validateEventDTO(EventDTO eventDTO, boolean isNew) throws AppsException {
        if (eventDTO.getArtistID() == null) {
            throw new AppsException("Artist ID is not valid");
        }
        if (!userService.existsById(eventDTO.getArtistID())) {
            throw new AppsException("Artist can't find");
        }
        if (eventDTO.getCustomerID() == null) {
            throw new AppsException("Customer ID is not valid");
        }
        if (!customerService.existsById(eventDTO.getCustomerID())) {
            throw new AppsException("Customer can't find");
        }
        if (StringUtils.isEmpty(eventDTO.getStartTime())) {
            throw new AppsException("Start time is not valid");
        }
        if (StringUtils.isEmpty(eventDTO.getEndTime())) {
            throw new AppsException("End time is not valid");
        }
        if (eventDTO.getCost() == null) {
            throw new AppsException("Cost is not valid");
        }
        if (StringUtils.isEmpty(eventDTO.getTattooLocation())) {
            throw new AppsException("Tattoo location is not valid");
        }
    }
}
