
package com.egg.appsalud.servicios;

import com.egg.appsalud.Enumeracion.Provincias;
import com.egg.appsalud.entidades.Direcciones;
import com.egg.appsalud.repositorios.DireccionesRepositorio;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionesServicio {
    
    @Autowired
    private DireccionesRepositorio direccionesRepositorio;
    
    @Transactional
    public Direcciones crearDirecciones(Provincias provincia, String localidad, String direccion){
           Direcciones direcciones = new Direcciones();
           direcciones.setProvincias(provincia);
           direcciones.setLocalidad(localidad);
           direcciones.setDireccion(direccion);
           direccionesRepositorio.save(direcciones);
           return direcciones;
    }
    
    @Transactional
    public Direcciones modificarDirecciones(String id, Provincias provincia, String localidad, String direccion){
        
        Optional<Direcciones> respuesta = direccionesRepositorio.findById(id);
       if(respuesta.isPresent()){
           Direcciones direcciones = respuesta.get();
           
           direcciones.setProvincias(provincia);
           direcciones.setLocalidad(localidad);
           direcciones.setDireccion(direccion);
           direccionesRepositorio.save(direcciones);
           return direcciones;
       }else{
           return null;
       }
        
    }
    
}
