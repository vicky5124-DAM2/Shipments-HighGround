package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Office;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface OfficeService {
    List<Office> findAll();

    Office getById(@NotBlank Long id);
}
