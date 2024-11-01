/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package umg.edu.gt.desarrollo.proyectofinal.mavenproject2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TipoCambioApplication implements CommandLineRunner {

    @Autowired
    private TipoCambioService tipoCambioService;

    public static void main(String[] args) {
        SpringApplication.run(TipoCambioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Solicitando tipo de cambio desde el servicio SOAP...");
        String respuesta = tipoCambioService.obtenerTipoCambioDia();
        System.out.println("Respuesta del servicio SOAP: " + respuesta);
    }
}

