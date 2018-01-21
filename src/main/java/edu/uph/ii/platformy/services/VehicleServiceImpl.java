package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.RoomFilter;
import edu.uph.ii.platformy.controllers.commands.SupplyFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private RoomRepository roomRepository;



    @Override
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public List<RoomType> getAllTypes() {
        return roomTypeRepository.findAll();
    }






    @Override
    public Page<Room> getAllVehicles(RoomFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = roomRepository.findAll(pageable);
        }else{
            page = roomRepository.findAllRoomUsingFilter(search.getPhraseLIKE(), search.getMaxPrice(), pageable);
        }

        return page;

    }

    @Transactional
    @Override
    public Room getVehicle(Long id) {
        Optional<Room> optionalVehicle = roomRepository.findById(id);
        Room room = optionalVehicle.orElseThrow( () -> new VehicleNotFoundException(id) );

        room.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return room;
    }


    @Override
    public void deleteVehicle(Long id) {
    // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(roomRepository.existsById(id) == true){
            roomRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }


    @Override
    public void saveVehicle(Room room) {
        roomRepository.save(room);
    }

}
