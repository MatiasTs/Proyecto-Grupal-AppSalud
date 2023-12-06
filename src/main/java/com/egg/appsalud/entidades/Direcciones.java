
package com.egg.appsalud.entidades;

import com.egg.appsalud.Enumeracion.Provincias;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Direcciones {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @Enumerated(EnumType.STRING)
    private Provincias provincias;
    
    private String localidad;
    private String direccion;

    
    
    
    
    
    public Direcciones(Provincias provincias, String localidad, String direccion){
        this.provincias = provincias;
        this.localidad = localidad;
        this.direccion = direccion;
    }
}
