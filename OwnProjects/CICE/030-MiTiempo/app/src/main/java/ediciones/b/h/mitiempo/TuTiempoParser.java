package ediciones.b.h.mitiempo;

import android.content.Context;
import android.graphics.Bitmap;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by V on 4/2/16.
 */
public class TuTiempoParser {

    public static class TiempoParserException extends Exception{
        public TiempoParserException(String detailMessage) {
            super(detailMessage);
        }
    }

    public static Tiempo getTiempoFromUrl(Context context, String stringUrl) throws TiempoParserException {
        // Hacer una conexión y descargar el xml.
        try {
            URL url = new URL(stringUrl);
            URLConnection connection = url.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection)connection;

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK){

                InputStream inputStream = httpURLConnection.getInputStream();
                return getTiempoFromInputStream(context, inputStream);

            }else{
                throw new TiempoParserException("ERROR en la conexion al servidor");
            }

        } catch (IOException e) {
            throw new TiempoParserException("URL no valida");
        }
    }

    public static Tiempo getTiempoFromInputStream(Context context, InputStream inputStream) throws TiempoParserException {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Element documentElement = document.getDocumentElement();

            NodeList horasNodelist = documentElement.getElementsByTagName("hora");

            ArrayList<PronosticoHora> pronosticoHoras = new ArrayList<>();

            for(int i = 0; i < horasNodelist.getLength(); i++){
                Element horaElement = (Element)horasNodelist.item(i);
                try{
                    PronosticoHora pronosticoHora = getPronosticoHoraFromElement(context,horaElement);
                    pronosticoHoras.add(pronosticoHora);
                }catch (TiempoParserException e){
                    e.printStackTrace();
                }
            }

            return new Tiempo("Madrid",pronosticoHoras.get(0).getFecha(),pronosticoHoras);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new TiempoParserException("No se pudo leer la información del tiempo");
        }

    }

    private static PronosticoHora getPronosticoHoraFromElement(Context context, Element horaElement) throws TiempoParserException {

        String fecha = getStringForTagName(horaElement,"fecha");
        String horadatos = getStringForTagName(horaElement,"hora_datos");
        String temperatura = getStringForTagName(horaElement,"temperatura");
        String texto = getStringForTagName(horaElement,"texto");
        String humedad = getStringForTagName(horaElement,"humedad");
        String presion = getStringForTagName(horaElement,"presion");
        String icono = getStringForTagName(horaElement, "icono");
        String viento = getStringForTagName(horaElement,"viento");
        String dirViento = getStringForTagName(horaElement,"dir_viento");
        String icoViento = getStringForTagName(horaElement,"ico_viento");



        PronosticoHora pronosticoHora  = new PronosticoHora(fecha, horadatos, temperatura, texto, humedad, presion, icono, viento, dirViento, icoViento);

        return pronosticoHora;
    }

    private static String getStringForTagName(Element element, String tagName) throws TiempoParserException {

        if(element.getElementsByTagName(tagName).item(0) != null){
            return element.getElementsByTagName(tagName).item(0).getFirstChild().getNodeValue();
        }else{
            throw new TiempoParserException("No existe la etiqueta");
        }

    }
}
