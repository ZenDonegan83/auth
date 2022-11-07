package com.inkd.auth.controller.event;

import com.inkd.auth.constants.AppsConstants;
import com.inkd.auth.exception.AppsException;
import com.inkd.auth.model.common.ResponseDTO;
import com.inkd.auth.model.dto.event.EventDTO;
import com.inkd.auth.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/allEvents", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<List<EventDTO>>> allEvents() {
        ResponseDTO<List<EventDTO>> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            List<EventDTO> allEvents = this.eventService.findAllEvents();

            response.setResult(allEvents);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @GetMapping(value = "/findByID/{eventID}", headers = "Accept=application/json")
    public ResponseEntity<ResponseDTO<EventDTO>> findByID(@PathVariable Long eventID) {
        ResponseDTO<EventDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            EventDTO event = this.eventService.findByID(eventID);

            response.setResult(event);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.OK;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PostMapping("saveEvent")
    public ResponseEntity<ResponseDTO<EventDTO>> saveEvent(@RequestBody EventDTO eventDTO) {
        ResponseDTO<EventDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            EventDTO savedEventDTO = eventService.saveEvent(eventDTO);

            response.setResult(savedEventDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;
        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }

    @PutMapping("/updateEvent/{eventID}")
    public ResponseEntity<ResponseDTO<EventDTO>> updateEvent(@PathVariable Long eventID, @RequestBody EventDTO updateEventDTO) {
        ResponseDTO<EventDTO> response = new ResponseDTO<>();
        HttpStatus httpStatus;

        try {
            EventDTO eventDTO = this.eventService.updateEvent(eventID, updateEventDTO);

            response.setResult(eventDTO);
            response.setStatus(AppsConstants.ResponseStatus.SUCCESS);
            httpStatus = HttpStatus.CREATED;

        } catch (AppsException e) {
            response.setStatus(AppsConstants.ResponseStatus.FAILED);
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setAppsErrorMessages(e.getAppsErrorMessages());
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}
