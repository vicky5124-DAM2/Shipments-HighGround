package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Action;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface ActionService {
    Action save(@NotNull @Valid Action action);
}
