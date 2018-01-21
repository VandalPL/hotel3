package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.SupplyFilter;
import edu.uph.ii.platformy.exceptions.VehicleNotFoundException;
import edu.uph.ii.platformy.models.Supply;
import edu.uph.ii.platformy.repositories.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public Page<Supply> getAllSupplies(SupplyFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = supplyRepository.findAll(pageable);
        }else{
            page = supplyRepository.findAllSupplyUsingFilter(search.getPhraseLIKE(), pageable);
        }

        return page;
    }
    @Transactional
    @Override
    public Supply getSupply(Long id) {
        Optional<Supply> optionalSupply = supplyRepository.findById(id);
        Supply supply = optionalSupply.orElseThrow( () -> new VehicleNotFoundException(id) );
        return supply;
    }
    @Override
    public void deleteSupply(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(supplyRepository.existsById(id) == true){
            supplyRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void saveSupply(Supply supply) {
        supplyRepository.save(supply);
    }
}

