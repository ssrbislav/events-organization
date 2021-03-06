package com.ftn.eventsorganization.service.impl;

import com.ftn.eventsorganization.DTO.SectorDTO;
import com.ftn.eventsorganization.exception.InvalidInputException;
import com.ftn.eventsorganization.exception.ObjectNotFoundException;
import com.ftn.eventsorganization.model.Hall;
import com.ftn.eventsorganization.model.Sector;
import com.ftn.eventsorganization.repository.SectorRepository;
import com.ftn.eventsorganization.service.IHallService;
import com.ftn.eventsorganization.service.ISectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorServiceImpl implements ISectorService {

    @Autowired
    SectorRepository sectorRepository;

    @Autowired
    IHallService hallService;

    @Override
    public List<Sector> findAll() {
        return sectorRepository.findAllByDeletedIsFalse();
    }

    @Override
    public Sector getOne(Long id) throws ObjectNotFoundException {
        return sectorRepository.findByIdAndDeletedIsFalse(id).orElseThrow(() ->
                new ObjectNotFoundException("Sector with id - " + id + " does not exist!"));
    }

    @Override
    public Sector create(SectorDTO dto) throws InvalidInputException, ObjectNotFoundException {
        Optional<Sector> sector;
        Hall hall;
        try {
            sector = Optional.of(new Sector());
            try {
                hall = hallService.getOne(dto.getHallId());
                sector.get().setHall(hall);
            } catch (ObjectNotFoundException ex) {
                throw new ObjectNotFoundException("Hall not found!", ex);
            }
            sector.get().setSectorMark(dto.getSectorMark());
            if(dto.getNumOfColumns() <= 0 || dto.getNumOfRows() <= 0) {
                throw new InvalidInputException();
            }
            sector.get().setNumOfColumns(dto.getNumOfColumns());
            sector.get().setNumOfRows(dto.getNumOfRows());
            return sectorRepository.save(sector.get());
        } catch (InvalidInputException ex) {
            throw new InvalidInputException("Rows and Columns must not be 0!", ex);
        }
    }

    @Override
    public Sector update(Sector sec) throws ObjectNotFoundException, InvalidInputException {
        Optional<Sector> sector;
        try {
            if(sec.getNumOfColumns() == 0 || sec.getNumOfRows() == 0) {
                throw new InvalidInputException();
            }
            sector = Optional.ofNullable(getOne(sec.getId()));
            sector.get().setNumOfRows(sec.getNumOfRows());
            sector.get().setNumOfColumns(sec.getNumOfColumns());
            sector.get().setSectorMark(sec.getSectorMark());
            return sectorRepository.save(sector.get());
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Sector does not exist!");
        } catch (InvalidInputException ex) {
            throw new InvalidInputException("Rows and Columns must not be 0!", ex);
        }
    }

    @Override
    public boolean delete(Long id) throws ObjectNotFoundException {
        try {
            Sector sector = getOne(id);
            sector.setDeleted(true);
            sectorRepository.save(sector);
            return sector.isDeleted();
        } catch (ObjectNotFoundException ex) {
            throw new ObjectNotFoundException("Sector not found!", ex);
        }
    }
}
