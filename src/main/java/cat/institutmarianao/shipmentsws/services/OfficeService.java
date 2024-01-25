package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Office;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface OfficeService {
    List<Office> findAll();

    Office getById(@Min(value = 1) Long id);
}
