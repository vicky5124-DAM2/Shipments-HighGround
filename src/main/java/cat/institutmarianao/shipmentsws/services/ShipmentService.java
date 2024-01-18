package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public interface ShipmentService {
    List<Shipment> findAll(Shipment.Status status, String receivedBy, String courierAssigned, Shipment.Category category, Date from, Date to);

    Shipment getById(@NotBlank Long id);
    List<Action> getTrackingById(@NotBlank Long id);

    Shipment save(@NotNull @Valid Shipment shipment);
    Shipment saveAction(@NotNull @Valid Action action);

    void deleteById(@NotBlank Long id);
}