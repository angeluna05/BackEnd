package com.example.ytalentbackend.Services;

import com.example.ytalentbackend.Models.Celulas;
import com.example.ytalentbackend.Models.Retos;
import com.example.ytalentbackend.Models.Programarsesion;
import com.example.ytalentbackend.Repository.CelulasRepository;
import com.example.ytalentbackend.Repository.RetosRepository;
import com.example.ytalentbackend.Repository.ProgramarsesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EstadoSchedulerService {

    @Autowired
    private CelulasRepository celulasRepository;
    @Autowired
    private RetosRepository retosRepository;
    @Autowired
    private ProgramarsesionRepository programarSesionRepository;

    // Tarea programada para ejecutarse cada 12 horas (00:00 y 12:00)
    @Scheduled(cron = "0 0 0,11 * * ?")
    public void actualizarEstadoProgramarSesion() {
        LocalDate fechaActual = LocalDate.now();

        programarSesionRepository.findAll().forEach(sesion -> {
            // Actualizar estado a "En curso" si la fecha de inicio es hoy y el estado es "Por empezar"
            if (sesion.getFecha() != null && sesion.getFecha().isEqual(fechaActual) && sesion.getEstado().equals("Por empezar")) {
                sesion.setEstado("En curso");
                programarSesionRepository.save(sesion);
            }

            // Finalizar sesión si la fecha y hora de fin han pasado
            LocalDateTime fechaHoraFin = sesion.getFecha().atTime(sesion.getHoraFin());
            if (fechaHoraFin.isBefore(LocalDateTime.now()) && !sesion.getEstado().equals("Finalizado")) {
                sesion.setEstado("Finalizado");
                programarSesionRepository.save(sesion);
            }
        });

        System.out.println("Estado de Programar Sesión actualizado.");
    }

    // Tarea programada para ejecutarse cada 11 horas (00:00 y 11:00)
    @Scheduled(cron = "0 0 0,11 * * ?")
    public void actualizarEstadoRetos() {
        LocalDate fechaActual = LocalDate.now();

        retosRepository.findAll().forEach(reto -> {
            // Actualizar estado a "En curso" si la fecha de inicio es hoy y el estado es "Por empezar"
            if (reto.getFechaInicio() != null && reto.getFechaInicio().isEqual(fechaActual) && reto.getEstado().equals("Por empezar")) {
                reto.setEstado("En curso");
                retosRepository.save(reto);
            }

            // Finalizar reto si la fecha de fin ha pasado
            if (reto.getFechaFin() != null && reto.getFechaFin().isBefore(fechaActual) && !reto.getEstado().equals("Finalizado")) {
                reto.setEstado("Finalizado");
                retosRepository.save(reto);
            }
        });

        System.out.println("Estado de Retos actualizado.");
    }

    // Tarea programada para ejecutarse todos los días a medianoche
    @Scheduled(cron = "0 0 0,11 * * ?")
    public void actualizarEstadoCelulas() {
        LocalDate fechaActual = LocalDate.now();

        celulasRepository.findAll().forEach(celula -> {
            // Actualizar estado a "En curso" si la fecha de inicio es hoy y el estado es "Por empezar"
            if (celula.getFechaInicio() != null && celula.getFechaInicio().isEqual(fechaActual) && celula.getEstado().equals("Por empezar")) {
                celula.setEstado("En curso");
                celulasRepository.save(celula);
            }

            // Finalizar célula si la fecha de fin ha pasado
            if (celula.getFechaFin() != null && celula.getFechaFin().isBefore(fechaActual) && !celula.getEstado().equals("Finalizada")) {
                celula.setEstado("Finalizada");
                celulasRepository.save(celula);
            }
        });

        System.out.println("Estado de Células actualizado.");
    }
}
