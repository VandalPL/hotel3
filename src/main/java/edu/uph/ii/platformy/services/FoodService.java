package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.FoodFilter;
import edu.uph.ii.platformy.models.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FoodService {
    //Page<Food> getAllFood( Pageable pageable);
    //public Page<Room> getAllVehicles(RoomFilter search, Pageable pageable)
     Page<Food> getAllFood(FoodFilter filter, Pageable pageable);
    Food getFood(Long id);
    void saveFood(Food food);
    void deleteFood(Long id);



}
