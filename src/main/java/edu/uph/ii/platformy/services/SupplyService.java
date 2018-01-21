package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.SupplyFilter;
import edu.uph.ii.platformy.models.Supply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface SupplyService {

    Page<Supply> getAllSupplies(SupplyFilter filter, Pageable pageable);

    void deleteSupply(Long id);

    Supply getSupply(Long id);

    void saveSupply(Supply supply);
}
