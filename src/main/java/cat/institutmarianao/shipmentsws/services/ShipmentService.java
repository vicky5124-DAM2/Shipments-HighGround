package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public interface ShipmentService {
    List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to);
    Shipment getById(@Min(value = 1) Long id);
    List<Action> getTrackingById(@Min(value = 1) Long id);
    Shipment save(@NotNull @Valid Shipment shipment);
    void deleteById(@NotBlank Long id);
}