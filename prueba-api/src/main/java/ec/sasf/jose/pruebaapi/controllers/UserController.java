package ec.sasf.jose.pruebaapi.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.sasf.jose.pruebaapi.models.Role;
import ec.sasf.jose.pruebaapi.models.Usuario;
import ec.sasf.jose.pruebaapi.services.UserService;

@RestController
@RequestMapping("/api/usuario")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService usuarioService;

    //@GetMapping
    //@PreAuthorize("hasRole('VIGILANTE') or hasRole('ADMIN')")

    /* @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listarUsuarios();
    } */

    @GetMapping
    public ResponseEntity<Usuario> callMyProcedure() {
        Usuario u = usuarioService.callMyProcedure(null);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasRole('VIGILANTE') or hasRole('ADMIN')")
    public List<Role> getRoles(){
        return usuarioService.listarAllRoles();
    } 
    
    /* @GetMapping("/{id}")
    @PreAuthorize("hasRole('VIGILANTE') or hasRole('ADMIN')")
    public Usuario getUsuarioById(@PathVariable("id") Long id ){
        return usuarioService.listarById(id);
    }  */

    /* @GetMapping("/roles/{roles}")
    // @PreAuthorize("hasRole('VIGILANTE') or hasRole('ADMIN')")
    public List<Usuario> listarPorPorRoles(@PathVariable("roles") String roles){
        return usuarioService.listarUsuariosPorRoles(roles);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertar(@RequestBody Usuario usuarioBody){
        return usuarioService.insertar(usuarioBody);
    } */
    
    @PutMapping("/editar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuarioBody){
        
        usuarioBody.setId(id);
        
        return usuarioService.actualizar( usuarioBody);
    }

    @PutMapping("/changepass/{id}")
    public ResponseEntity<?> editarContraseniaPut(@PathVariable("id") Long id, @RequestBody Map<String, String> data) {
        return usuarioService.editarContrasenia(id, data);
    }

    /* @PutMapping("/eliminar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> eliminar(@PathVariable Long id){
        return usuarioService.eliminar(id);
    } */ 
}
