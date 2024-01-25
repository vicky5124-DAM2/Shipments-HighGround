package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Office;
import cat.institutmarianao.shipmentsws.repositories.OfficeRepository;
import cat.institutmarianao.shipmentsws.services.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    @Override
    public List<Office> findAll() {
        return officeRepository.findAll();
    }

    @Override
    public Office getById(Long id) {
        return officeRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
