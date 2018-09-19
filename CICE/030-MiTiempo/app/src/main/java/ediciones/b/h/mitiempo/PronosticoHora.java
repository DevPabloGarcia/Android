package ediciones.b.h.mitiempo;

import android.graphics.Bitmap;

/**
 * Created by V on 4/2/16.
 */
public class PronosticoHora {

    private String fecha;
    private String horadatos;
    private String temperatura;
    private String texto;
    private String humedad;
    private String presion;
    private String iconoUrl;
    private Bitmap icono;
    private String viento;
    private String dirViento;
    private String icoVientoUrl;
    private Bitmap icoViento;

    public PronosticoHora(String fecha, String horadatos, String temperatura, String texto, String humedad, String presion, String icono, String viento, String dirViento, String icoViento) {
        this.fecha = fecha;
        this.horadatos = horadatos;
        this.temperatura = temperatura;
        this.texto = texto;
        this.humedad = humedad;
        this.presion = presion;
        this.iconoUrl = icono;
        this.viento = viento;
        this.dirViento = dirViento;
        this.icoVientoUrl = icoViento;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHoradatos() {
        return horadatos;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getTexto() {
        return texto;
    }

    public String getHumedad() {
        return humedad;
    }

    public String getPresion() {
        return presion;
    }

    public String getIconoUrl() {
        return iconoUrl;
    }

    public String getViento() {
        return viento;
    }

    public String getDirViento() {
        return dirViento;
    }

    public String getIcoVientoUrl() {
        return icoVientoUrl;
    }

    public Bitmap getIcoViento() {
        return icoViento;
    }

    public Bitmap getIcono() {
        return icono;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHoradatos(String horadatos) {
        this.horadatos = horadatos;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public void setIconoUrl(String iconoUrl) {
        this.iconoUrl = iconoUrl;
    }

    public void setIcono(Bitmap icono) {
        this.icono = icono;
    }

    public void setViento(String viento) {
        this.viento = viento;
    }

    public void setDirViento(String dirViento) {
        this.dirViento = dirViento;
    }

    public void setIcoVientoUrl(String icoVientoUrl) {
        this.icoVientoUrl = icoVientoUrl;
    }

    public void setIcoViento(Bitmap icoViento) {
        this.icoViento = icoViento;
    }
}
