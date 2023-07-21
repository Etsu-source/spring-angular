package ec.sasf.jose.pruebaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ec.sasf.jose.pruebaapi.models.Usuario;

@Repository
public interface MyProcedureRepository extends CrudRepository<Usuario, Long> {

    /* @Procedure(name = "my_procedure5(4)")
    void my_procedure5(); */

    /* @Procedure(name = "my_procedure5")
    List<Usuario> prPersona(@Param("4") Integer idInteger); */
}
