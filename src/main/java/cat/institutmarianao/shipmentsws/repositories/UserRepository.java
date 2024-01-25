package cat.institutmarianao.shipmentsws.repositories;

import cat.institutmarianao.shipmentsws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
}
