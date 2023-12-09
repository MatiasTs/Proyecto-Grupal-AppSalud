package com.egg.appsalud.controladores;

import com.egg.appsalud.Exception.MiException;
import com.egg.appsalud.entidades.Usuario;
import com.egg.appsalud.servicios.UsuarioServicio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UsuarioServicio usuarioServicio;

    @GetMapping("/dashboard")
    public String panelAdmin(@Param("palabra") String palabra, ModelMap modelo) {

        //var usuarios = usuarioServicio.listarTodos();

        //modelo.addAttribute("usuarios", usuarios);
        
        List<Usuario> usuarios = usuarioServicio.listarUsuario(palabra);
        modelo.addAttribute("usuarios", usuarios);

        

        modelo.addAttribute("palabra", palabra);

        return "dashboard";
    }
    
    @GetMapping("/modificar/{id}")
    public String modificarAdmin(@PathVariable String id, ModelMap modelo) {

        
        Usuario admin = usuarioServicio.getOne(id);
        modelo.addAttribute("admin", admin);
        
        System.out.println("********************************");
        
        System.out.println("ADMINNNNNNNNNNN" + admin);
        System.out.println("********************************");
        

        return "admin_modificar";
    }

    @PostMapping("/modificar/{id}")
    public String modificarAdmin(@PathVariable String id, MultipartFile archivo, @RequestParam String nombreUsuario, @RequestParam String nombre, @RequestParam String apellido,
                                       @RequestParam(required = false) Long DNI, @RequestParam("fechaDeNacimiento") String fechaDeNacimientoStr, @RequestParam String email, @RequestParam String password, @RequestParam String password2,
                                       @RequestParam String direccion, ModelMap modelo) {

        Date fechaDeNacimiento;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaDeNacimiento = dateFormat.parse(fechaDeNacimientoStr);

        } catch (ParseException p) {
            modelo.put("error", "la fecha no puede venir vacía");
            return "redirect:/admin/modificar/{id}";
        }

        try {

            usuarioServicio.modificarUsuario(id, archivo, nombreUsuario, nombre, apellido, DNI, fechaDeNacimiento, email, password, password2, true);
            modelo.put("exito", "Profesional modificado con exito");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "redirect:/admin/{id}";

        }
        return "dashboard.html";
    }
}
