package uniquindio.Services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import uniquindio.Model.Envio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ReportService {

    /**
     * Genera un PDF con los envíos proporcionados.
     *
     * @param envios     Lista de envíos a incluir en el PDF.
     * @param rutaArchivo Ruta donde se guardará el PDF.
     * @throws DocumentException Si ocurre un error al generar el documento.
     * @throws IOException       Si ocurre un error al escribir el archivo.
     */
    public static void exportarEnviosPDF(List<Envio> envios, String rutaArchivo)
            throws DocumentException, IOException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
        document.open();

        // Título del reporte
        document.add(new Paragraph("Reporte de envíos\n\n"));

        // Tabla con 6 columnas
        PdfPTable table = new PdfPTable(6);
        table.addCell("ID");
        table.addCell("Tipo Dirección");
        table.addCell("Estado");
        table.addCell("Costo");
        table.addCell("Fecha Creación");
        table.addCell("Fecha Estimada");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Agregar datos de los envíos
        for (Envio envio : envios) {
            table.addCell(String.valueOf(envio.getIdEnvio()));
            table.addCell(envio.getTipoDireccion());
            table.addCell(envio.getEstado().toString());
            table.addCell(String.valueOf(envio.getCosto()));
            table.addCell(envio.getFechaCreación() != null ? sdf.format(envio.getFechaCreación()) : "-");
            table.addCell(envio.getFechaEstimada() != null ? sdf.format(envio.getFechaEstimada()) : "-");
        }

        document.add(table);
        document.close();
    }
}
