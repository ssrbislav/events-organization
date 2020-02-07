package com.ftn.eventsorganization.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.eventsorganization.DTO.ResponseMessage;
import com.ftn.eventsorganization.DTO.TicketDto;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Event;
import com.ftn.eventsorganization.security.JwtProvider;
import com.ftn.eventsorganization.service.TicketService;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketController {
	@Autowired
	private TicketService ticketService;
	@Autowired
	private JwtProvider provider;
	
	@PostMapping(value = "/reservation", consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity reservation (@RequestBody List<TicketDto> reservationTickets, HttpServletRequest http){
		HttpServletRequest httpServletRequest = (HttpServletRequest)http;
        String authToken = httpServletRequest.getHeader("Authorization");
        System.out.println(authToken);
        String username = this.provider.getUserNameFromJwtToken(authToken);
        System.out.println("Ulogovan korisnik je: " + username);
        
        ResponseMessage msg = new ResponseMessage();
        try {
			boolean succsses  = ticketService.reservation(reservationTickets, username);
			if(succsses) {
				msg.setMessage("You successfully reserved your tickets!");
			} else {
				msg.setMessage("Your reservation failed!");
			}
			
		} catch (ObjectNotFoundException | InvalidInputException e) {
			msg.setMessage(e.getMessage());
			return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
		}
        
        return new ResponseEntity<>(msg, HttpStatus.OK);    
	}
	
	@PreAuthorize("hasRole('ROLE_VISITOR')")
    @GetMapping("/{id}")
    public ResponseEntity findOne(@PathVariable Long id) throws ObjectNotFoundException {

		ResponseMessage msg = new ResponseMessage();
        try {
            boolean d = ticketService.cancelReservation(id);
            if (d == true){
            	msg.setMessage("Successfully canceled reservation!");
            	return new ResponseEntity<>(msg, HttpStatus.OK);
            }
        } catch (ObjectNotFoundException ex) {
        	msg.setMessage("Unsuccessful try of canceling reservation!");
            return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        }
        msg.setMessage("Unsuccessful try of canceling reservation!");
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
	
}
