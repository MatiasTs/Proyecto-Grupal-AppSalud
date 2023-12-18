package com.egg.appsalud.controladores;

import com.egg.appsalud.Exception.MiException;
import com.egg.appsalud.entidades.Consulta;
import com.egg.appsalud.entidades.Paciente;
import com.egg.appsalud.entidades.Turno;
import com.egg.appsalud.repositorios.ConsultaRepositorio;
import com.egg.appsalud.servicios.ConsultaServicio;
import com.egg.appsalud.servicios.PacienteServicio;
import com.egg.appsalud.servicios.ProfesionalServicio;
import com.egg.appsalud.servicios.TurnoServicio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/paciente")
public class PacienteControlador {

    @Autowired
    PacienteServicio pacienteServicio;

    @Autowired
    ProfesionalServicio profesionalServicio;

    @Autowired
    ConsultaServicio consultaServicio;

    @Autowired
    TurnoServicio turnoServicio;

    @GetMapping("/solicitar/{id}")
    public String solicitarCita(@PathVariable String id, ModelMap modelo, HttpSession session) {

        if (session.getAttribute("usuariosession") == null) {
            return "login";
        } else {

//            Consulta consulta = consultaServicio.buscarPorIdPaciente(session.getId());

//            modelo.addAttribute("consulta",consulta);

            var profesional = profesionalServicio.getOne(id);
            if (profesional != null) {

                List<Turno> turnos = turnoServicio.obtenerTurnosDeProfesionalOrdenados(profesional);
                modelo.addAttribute("turnos", turnos);
                modelo.addAttribute("profesional", profesional);

                return "cita_solicitud";
            } else {
                return "error";
            }
        }
    }

    @PostMapping("/solicitar/{id}")
    public String reservarCita(@PathVariable String id, @RequestParam String idProfesional,
                               @RequestParam Date fecha, @RequestParam LocalTime horario,
                               HttpServletRequest request, ModelMap model) throws MiException, ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = dateFormat.parse(request.getParameter("fecha"));

        var Paciente = pacienteServicio.getOne(id);
        var Profesional = profesionalServicio.getOne(idProfesional);

//        consultaServicio.crearConsulta(Paciente, Profesional, fechaDate, horario);

        return "redirect:/paciente/citas";
    }

    @GetMapping("/historia")
    public String historiaClinica(HttpSession session, ModelMap modelMap) {

        Paciente paciente = (Paciente) session.getAttribute("usuariosession");

        var consultas = consultaServicio.obtenerConsultasDelPaciente(paciente);

        modelMap.addAttribute("consultas", consultas);
        return "historia_clinica";
    }


    //    MODIFICAR DATOS COMO ADMIN
    /*@GetMapping("/modificar/{id}")
    public String modificarPaciente() {
        return "usuarioModificar";
    }*/
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/modificar/{id}")
    public String modificarPaciente(@PathVariable String id, ModelMap modelo) {

        
        Paciente paciente = pacienteServicio.getOne(id);
        modelo.addAttribute("paciente", paciente);

        return "paciente_modificar";
    }

    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/modificar/{id}")
    public String modificarPaciente(@PathVariable String id, MultipartFile archivo, @RequestParam String nombreUsuario, @RequestParam String nombre, @RequestParam String apellido,
                                       @RequestParam(required = false) Long DNI, @RequestParam("fechaDeNacimiento") String fechaDeNacimientoStr, @RequestParam String email, @RequestParam String password, @RequestParam String password2,
                                       @RequestParam String direccion, ModelMap modelo) {

        Date fechaDeNacimiento;

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fechaDeNacimiento = dateFormat.parse(fechaDeNacimientoStr);

        } catch (ParseException p) {
            modelo.put("error", "la fecha no puede venir vacía");
            
            return "index.html";
        }

        try {
            
            if(archivo.isEmpty()){
                
                archivo = null;
            }

            pacienteServicio.modificarPacientes(archivo, id, nombreUsuario, nombre, apellido, DNI, fechaDeNacimiento, email, password, password2);
            modelo.put("exito", "Profesional modificado con exito");
            
            System.out.println("**********************************************************************");
            System.out.println("Error en modificar paciente");
            System.out.println("**********************************************************************");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "index.html";

        }
        return "dashboard.html";
    }

}

