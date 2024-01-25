package cat.institutmarianao.shipmentsws.services.impl;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.repositories.ActionRepository;
import cat.institutmarianao.shipmentsws.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    private ActionRepository actionRepository;

    @Override
    public Action save(Action action) {
        return actionRepository.saveAndFlush(action);
    }
}
