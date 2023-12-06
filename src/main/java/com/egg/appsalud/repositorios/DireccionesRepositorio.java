
package com.egg.appsalud.repositorios;

import com.egg.appsalud.entidades.Direcciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionesRepositorio extends JpaRepository<Direcciones, String>{
    
}
