package cat.institutmarianao.shipmentsws.services;

import cat.institutmarianao.shipmentsws.model.Company;
import jakarta.validation.constraints.Min;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();

    Company getById(@Min(value = 1) Long id);
}
