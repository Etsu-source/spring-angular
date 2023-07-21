package ec.sasf.jose.pruebaapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import ec.sasf.jose.pruebaapi.models.Usuario;

public interface UserRepository extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsername(String username);

  /* Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  @Modifying
  @Query("UPDATE Usuario u SET u.status = null WHERE u.id = ?1")
  public void deleteById(Long id);

  @Query("SELECT u FROM Usuario u WHERE u.status != null ORDER BY u.id ASC")
  public List<Usuario> findByEstado();

  @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.name = :roleName")
  List<Usuario> findByRoles(@Param("roleName") String roleName); */

  //procedimiento almacenado
  /* @Procedure(name = "my_procedure")
  void listaProcedure(); */

  //List<Usuario> listaProcedure();

  @Query(nativeQuery = true, value = "my_procedure5()")
  List<Usuario> listarUsuarios();
}
