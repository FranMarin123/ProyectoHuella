package example.tests.habito;

import example.model.entity.Habito;
import example.model.entity.HabitoId;
import example.model.services.ActividadService;
import example.model.services.HabitoService;
import example.model.services.UsuarioService;

import java.time.LocalDate;

public class RegistrarHabito {
    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        ActividadService actividadService = new ActividadService();
        HabitoService habitoService = new HabitoService();
        Habito habito = new Habito();
        HabitoId habitoId = new HabitoId();
        habito.setTipo("Semanal");
        //habitoId.setIdUsuario(usuarioService.findUser("francisco@gmail.com").getId());
        habito.setFrecuencia(5);
        habito.setUltimaFecha(LocalDate.now());
        //habitoId.setIdActividad(actividadService.findActividadById(1).getId());
        habito.setId(habitoId);
        habito.setIdActividad(actividadService.findActividadById(2));
        habito.setIdUsuario(usuarioService.findUser("francisco@gmail.com"));
        habitoService.saveHabito(habito);
    }
}
