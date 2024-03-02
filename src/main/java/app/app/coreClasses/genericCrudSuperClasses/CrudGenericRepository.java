package app.app.coreClasses.genericCrudSuperClasses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
public interface CrudGenericRepository<T extends CrudGenericEntity> extends JpaRepository<T, UUID> {
}
