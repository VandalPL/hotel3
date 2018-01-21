package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.RoomFilter;
import edu.uph.ii.platformy.models.Reservation;
import edu.uph.ii.platformy.models.Room;
import edu.uph.ii.platformy.services.CartService;
import edu.uph.ii.platformy.services.ReservationService;
import edu.uph.ii.platformy.services.UserService;
import edu.uph.ii.platformy.services.VehicleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

@SessionAttributes(names={"USER"})
@Log4j2
public class ReservationController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private VehicleService vehicleService;




	@RequestMapping(value="/reservation.html", method = {RequestMethod.GET, RequestMethod.POST})
	public String showReservationList(Model model, @ModelAttribute("searchCommand") RoomFilter search, Pageable pageable){
		model.addAttribute("reservationListPage", reservationService.getAllReservations(pageable));
		return "reservation";
	}

	@Transactional
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/reservation.html",params = {"id","id_room"},method = {RequestMethod.GET, RequestMethod.POST})
	//@ResponseStatus(HttpStatus.CREATED)
	public String deleteReservation(@ModelAttribute("vehicle") Room room, Reservation reservation, int id, long id_room){

//		id_room = reservation.getId_room();
		room = vehicleService.getVehicle(id_room);
        room.setReserv_id(0);
        vehicleService.saveVehicle(room);


        reservation = reservationService.getReservation(id);
        reservation.setReserv_id(0);
        reservationService.saveReservation(reservation);
		//vehicleService.saveVehicle(room);
	//	reservationService.deleteReservation(id);
		return "confirmation";
	}



	//@ModelAttribute("USER")
	@GetMapping(value = "/vehicleList.html" , params = "id_reservation")
	public String Cart(@ModelAttribute("vehicle") Room room, long id_reservation) {
		//vehicleService.deleteVehicle(did);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Long id = userService.getUserID(currentPrincipalName);
		room=vehicleService.getVehicle(id_reservation);
		room.setReserv_id(1);
		vehicleService.saveVehicle(room);

		Reservation reservation = new Reservation(id_reservation, id);
		reservation.setReserv_id(1);
		//// zapis wykorzystac metode na podstawie save Vehicle
		reservationService.saveReservation(reservation);


		return "/confirmation";

	}





//	@Secured("ROLE_ADMIN")
//	@PostMapping(value="/bar.html")
//	//@ResponseStatus(HttpStatus.CREATED)
//	public String EditAdd(@Valid @ModelAttribute("food") Food v, BindingResult errors){
//
//		if(errors.hasErrors()){
//			return "bar";
//		}
//
//		//	log.info("Data utworzenia komponentu "+v.getCreatedDate());
//		//	log.info("Data edycji komponentu "+new Date());
//
//		foodService.saveFood(v);
//
//		return "redirect:bar.html";//po udanym dodaniu/edycji przekierowujemy na listę
//	}



}

