package br.com.casadocodigo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;

@Repository
@Transactional
public class UsuarioDAO implements UserDetailsService{

	@PersistenceContext
	private EntityManager manager;

	public Usuario loadUserByUsername(String email) {
		List<Usuario> usuarios = manager.createQuery("select u from Usuario u where email = :email", Usuario.class)
				.setParameter("email", email)
				.getResultList();
		
		if(usuarios.isEmpty()) {
			throw new UsernameNotFoundException("Usuario " + email + " não foi encontrado");
		}
		
		return usuarios.get(0);
	}
	
	public void alterRoles(String email, List<Role> roles) {
		Usuario u = manager.find(Usuario.class, email);
		System.out.println(roles);
		u.setRoles(roles);
	}
	
	public Usuario findByEmail(String email) {
		Usuario u = manager.find(Usuario.class, email);
		return u;
	}
	
	public boolean emailExists(String email) {
        List<Usuario> usuarios = manager.createQuery("select u from Usuario u WHERE u.email LIKE :email")
            .setParameter("email", email).getResultList();
        
        if(!usuarios.isEmpty()){
        	return true;
        }

        return false;
    }

	public void gravar(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public List<Usuario> listar() {
		return manager.createQuery("select u from Usuario u", Usuario.class)
				.getResultList();
	}
}