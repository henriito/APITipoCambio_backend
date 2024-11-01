/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package umg.edu.gt.desarrollo.proyectofinal.mavenproject2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/tipocambio")
public class TipoCambioController {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    @GetMapping("/actual")
    public TipoCambio obtenerTipoCambioActual() {
        Optional<TipoCambio> tipoCambio = Optional.ofNullable(tipoCambioRepository.findTopByOrderByIdDesc());
        
        if (tipoCambio.isPresent()) {
            System.out.println("Tipo de cambio encontrado: " + tipoCambio.get().getTipoCambio());
            return tipoCambio.get();
        } else {
            System.out.println("No se encontró ningún tipo de cambio en la base de datos.");
            return null;
        }
    }
}


