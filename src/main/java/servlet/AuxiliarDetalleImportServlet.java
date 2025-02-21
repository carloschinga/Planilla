/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import dao.AuxiliarDetalleDAO;
import dto.AuxiliarDetalle;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author san21
 */
@WebServlet(name = "AuxiliarDetalleImportServlet", urlPatterns = {"/auxiliardetalleImportservlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AuxiliarDetalleImportServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");  // Set response type to JSON

        // Obtener el archivo Excel del formulario
        Part filePart = request.getPart("fileInput");

        if (filePart != null) {
            // Crear directorio uploads si no existe
            File uploadDir = new File(getServletContext().getRealPath("/") + "uploads/");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            try {
                // Validar si el archivo es un .xlsx
                String fileName = filePart.getSubmittedFileName();
                if (!fileName.endsWith(".xlsx")) {
                    response.getWriter().write("{\"status\":\"error\",\"message\":\"El archivo debe ser .xlsx\"}");
                    return;
                }

                // Crear archivo en el directorio uploads
                File file = new File(uploadDir, filePart.getSubmittedFileName());
                filePart.write(file.getAbsolutePath());  // Escribir archivo en el disco

                // Procesar el archivo Excel
                List<AuxiliarDetalle> detalles = procesarExcel(file);

                // Crear el DAO para insertar los datos
                AuxiliarDetalleDAO dao = new AuxiliarDetalleDAO(getEntityManagerFactory());

                // Insertar los datos en la base de datos
                dao.importarDatos(detalles);

                // Responder con un mensaje de éxito
                response.getWriter().write("{\"status\":\"success\",\"message\":\"Archivo importado correctamente\"}");

            } catch (IOException e) {
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Error al leer el archivo Excel: " + e.getMessage() + "\"}");
            } 
        } 
    }

    private List<AuxiliarDetalle> procesarExcel(File file) throws IOException {
        List<AuxiliarDetalle> detalles = new ArrayList<>();

        // Leer el archivo Excel
        try (FileInputStream fis = new FileInputStream(file); Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // Obtener la primera hoja
            Iterator<Row> rowIterator = sheet.iterator();

            // Saltar la primera fila (cabecera)
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            // Leer las filas del archivo Excel
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                try {
                    AuxiliarDetalle detalle = new AuxiliarDetalle();

                    // Obtener los valores de las celdas y asignarlos al objeto AuxiliarDetalle
                    detalle.setCodiAux((int) row.getCell(0).getNumericCellValue());
                    detalle.setCodiPers((int) row.getCell(1).getNumericCellValue());
                    detalle.setDescDeta(row.getCell(2).getStringCellValue());
                    detalle.setMontDeta(new BigDecimal(row.getCell(3).getNumericCellValue()));

                    // Agregar el detalle a la lista
                    detalles.add(detalle);
                } catch (Exception e) {
                    e.printStackTrace();
                    // Si ocurre un error al procesar una fila, lo podemos registrar
                    throw new IOException("Error al procesar una fila en el archivo Excel: " + e.getMessage());
                }
            }
        }

        return detalles;
    }

    // Método para obtener el EntityManagerFactory
    // Método para obtener el EntityManagerFactory con el nombre correcto del persistence unit
    private EntityManagerFactory getEntityManagerFactory() {
        return javax.persistence.Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
    }

}
