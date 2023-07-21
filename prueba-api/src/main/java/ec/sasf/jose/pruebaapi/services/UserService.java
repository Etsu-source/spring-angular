package ec.sasf.jose.pruebaapi.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ec.sasf.jose.pruebaapi.models.Role;
import ec.sasf.jose.pruebaapi.repository.MyProcedureRepository;
import ec.sasf.jose.pruebaapi.repository.RoleRepository;
import ec.sasf.jose.pruebaapi.repository.UserRepository;
import ec.sasf.jose.pruebaapi.utils.MessageResponse;

import ec.sasf.jose.pruebaapi.models.Usuario;

@Service
public class UserService {
    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;
    
    private PasswordEncoder encoder;

    @Autowired
    private MyProcedureRepository myProcedureRepository;

    /* public ResponseEntity<?> insertar(Usuario user) {
        if (camposUnicosYnoNulos(user).getStatusCode().is4xxClientError()) {
            return camposUnicosYnoNulos(user);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        usuarioRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));
    } */

    public Usuario actualizar(Usuario user) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(user.getId());
        if (optionalUsuario == null) { //VERIFICAR LA LOGICA
            return null;
        }
        Usuario usuarioEditado = optionalUsuario.get();
        copiarCamposNoNulos(user, usuarioEditado);
        return usuarioRepository.save(usuarioEditado);
    }

    /* public List<Usuario> listarTodos() {
        return usuarioRepository.findByEstado();
    } */
    
    /* public List<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    } */

    
    public UserService(MyProcedureRepository myProcedureRepository) {
        this.myProcedureRepository = myProcedureRepository;
    }

    public Usuario callMyProcedure(Integer persona) {
        myProcedureRepository.listarUsuarios();
        return null;
        
    }



    public List<Role> listarAllRoles() {
        return roleRepository.findAllRoles();
    }

    public Usuario listarById(Long id) {
        return usuarioRepository.findById(id).get();
    }

    /* public List<Usuario> eliminar(Long id) {
        usuarioRepository.deleteById(id);
        return usuarioRepository.findByEstado();
    } */

    // en la ruta /api/auth/signup va a guardar nuevo usuario
    /* public ResponseEntity<?> registrar(@Valid Usuario signUpRequest) {

        if (camposUnicosYnoNulos(signUpRequest).getStatusCode().is4xxClientError()) {
            return camposUnicosYnoNulos(signUpRequest);
        }
        Set<Role> strRoles = roleRepository.getRoleUser();
        signUpRequest.setRoles(strRoles);
        signUpRequest.setStatus("A");
        signUpRequest.setPassword(encoder.encode(signUpRequest.getPassword()));

        usuarioRepository.save(signUpRequest);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado con exito!"));
    } */

    // Metodo para copiar campos no nulos
    private void copiarCamposNoNulos(Usuario fuente, Usuario destino) {
        if (fuente.getFirstname() != null) {
            destino.setFirstname(fuente.getFirstname());
        }
        if (fuente.getLastname() != null) {
            destino.setLastname(fuente.getLastname());
        }
        if (fuente.getEmail() != null) {
            destino.setEmail(fuente.getEmail());
        }
        if (fuente.getPassword() != null) {
            destino.setPassword(encoder.encode(fuente.getPassword()));
        }
        if (fuente.getRoles().size() > 0) {
            destino.setRoles(fuente.getRoles());
        }
        if (fuente.getStatus() != null) {
            destino.setStatus(fuente.getStatus());
        }
    }

    // Metodo para verificar email, usuario existente y campos vacios
    /* private ResponseEntity<?> camposUnicosYnoNulos(Usuario user) {
        if (usuarioRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error el usuario ya existe!"));
        }
        if (usuarioRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email ya utilizado anteriormente!"));
        }
        if (user.getUsername() == null || user.getEmail() == null ||
                user.getLastname() == null || user.getFirstname() == null ||
                user.getPassword() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Campos vacios!"));
        }
        return ResponseEntity.ok(new MessageResponse("Usuario registrado satisfactoriamente!"));
    } */

    // metodo para cambiar la clave
    public ResponseEntity<?> editarContrasenia(Long id, Map<String, String> data) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            String clave = data.get("lastpassword");
            String claveNueva = data.get("newpassword");
            if (encoder.matches(clave, usuario.getPassword())) {
                usuario.setPassword(encoder.encode(claveNueva));
                usuarioRepository.save(usuario);
                return ResponseEntity.ok(new MessageResponse("Contraseña actualizada satisfactoriamente!"));
            } else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Contraseña incorrecta!"));
            }
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Usuario no encontrado!"));
    }

    /* public List<Usuario> listarUsuariosPorRoles(String roles) {
        return usuarioRepository.findByRoles(roles);
    } */

}
