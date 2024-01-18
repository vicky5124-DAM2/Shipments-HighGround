package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Override
    public List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to) {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment getById(Long id) {
        return shipmentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Action> getTrackingById(Long id) {
        return shipmentRepository.findById(id).orElseThrow(NotFoundException::new).getTracking();
    }

    @Override
    public Shipment save(Shipment shipment) {
        return shipmentRepository.saveAndFlush(shipment);
    }

    @Override
    public Shipment saveAction(Action action) {
        Shipment dbShipment = this.getById(action.getShipment().getId());
        dbShipment.addTracking(action);
        return shipmentRepository.saveAndFlush(dbShipment);
    }

    @Override
    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }
}
