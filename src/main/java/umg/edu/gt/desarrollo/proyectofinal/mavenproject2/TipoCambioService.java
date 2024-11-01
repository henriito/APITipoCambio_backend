package umg.edu.gt.desarrollo.proyectofinal.mavenproject2;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.time.LocalDateTime;

@Service
public class TipoCambioService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public String obtenerTipoCambioDia() {
        String numeroSolicitud = LocalDateTime.now().toString();
        String requestPayload = "<TipoCambioDiaString xmlns=\"http://www.banguat.gob.gt/variables/ws/\"/>";

        // Enviar la solicitud SOAP y recibir la respuesta
        String response = (String) webServiceTemplate.marshalSendAndReceive(requestPayload);

        // Analizar la respuesta SOAP para extraer el tipo de cambio
        Double tipoCambioValor = extraerTipoCambioDeRespuesta(response);

        // Crear un registro TipoCambio y guardarlo en la base de datos
        TipoCambio tipoCambio = new TipoCambio();
        tipoCambio.setRequestNumber(numeroSolicitud);
        tipoCambio.setReply(response);
        tipoCambio.setTipoCambio(tipoCambioValor); // Asigna el valor extraído
        tipoCambioRepository.save(tipoCambio);

        return response;
    }

    private Double extraerTipoCambioDeRespuesta(String response) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(response)));

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//TipoCambioDiaResult/dblTipoCambio"; // Ajusta esto a la estructura exacta del XML
            Node node = (Node) xPath.compile(expression).evaluate(document, XPathConstants.NODE);

            return node != null ? Double.parseDouble(node.getTextContent()) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



