package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;

@Validated
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to) {
        // TODO specification
        return shipmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Shipment getById(@Min(value = 1) Long id) {
        return shipmentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Action> getTrackingById(@Min(value = 1) Long id) {
        return shipmentRepository.findById(id).orElseThrow(NotFoundException::new).getTracking();
    }

    @Override
    public Shipment save(@NotNull @Valid Shipment shipment) {
        return shipmentRepository.saveAndFlush(shipment);
    }

    @Override
    public void deleteById(@NotBlank Long id) {
        shipmentRepository.deleteById(id);
    }
}
