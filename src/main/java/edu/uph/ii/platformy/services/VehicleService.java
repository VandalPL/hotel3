package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.RoomFilter;
import edu.uph.ii.platformy.controllers.commands.SupplyFilter;
import edu.uph.ii.platformy.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehicleService {

    List<Accessory> getAllAccessories();

    List<RoomType> getAllTypes();




    Page<Room> getAllVehicles(RoomFilter filter, Pageable pageable);

    Room getVehicle(Long id);


    void deleteVehicle(Long id);

    void saveVehicle(Room room);

}
