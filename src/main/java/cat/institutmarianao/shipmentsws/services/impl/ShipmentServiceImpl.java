package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Courier;
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
import java.util.Locale;
import java.util.Objects;

@Validated
@Service
public class ShipmentServiceImpl implements ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to) { // TODO specification
        return shipmentRepository
                .findAll()
                .stream()
                .filter(i -> {
                    if (status != null) {
                        return i.getStatus() == status;
                    }
                    return true;
                })
                .filter(i -> {
                    if (receivedBy != null) {
                        return Objects.equals(i.getRecipient().getName().toLowerCase(Locale.ROOT), receivedBy.toLowerCase());
                    }
                    return true;
                })
                .filter(i -> {
                    if (courierAssigned != null) {
                        Assignment x = (Assignment) i.getTracking().stream().filter(j -> j instanceof Assignment).toList().get(0);
                        return Objects.equals(x.getCourier().getFullName().toLowerCase(Locale.ROOT), courierAssigned.toLowerCase());
                    }
                    return true;
                })
                .filter(i -> {
                    if (category != null) {
                        return i.getCategory() == category;
                    }
                    return true;
                })
                .filter(i -> {
                    if (from != null) {
                        if (i.getTracking().get(0) != null) {
                            return i.getTracking().get(0).getDate().after(from);
                        }
                        return true;
                    }
                    return true;
                })
                .filter(i -> {
                    if (to != null) {
                        if (i.getTracking().get(i.getTracking().size()) != null) {
                            return i.getTracking().get(0).getDate().before(to);
                        }
                        return true;
                    }
                    return true;
                })
                .toList();
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
