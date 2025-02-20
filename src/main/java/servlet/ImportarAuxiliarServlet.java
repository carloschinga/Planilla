/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import dao.AuxiliarDetalleDAO;
import dto.AuxiliarDetalle;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author san21
 */
@WebServlet(name = "ImportarAuxiliarServlet", urlPatterns = {"/ImportarAuxiliarServlet"})
public class ImportarAuxiliarServlet extends HttpServlet {

    // Crea un EntityManagerFactory usando la unidad de persistencia que tienes en tu proyecto
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("tuUnidadDePersistencia");

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        Part filePart = request.getPart("file"); // Obtén el archivo enviado
        InputStream fileContent = filePart.getInputStream();
    System.out.println("Punto de interrupción alcanzado");  // Agrega esto como prueba

        // Crea una instancia de tu DAO
        AuxiliarDetalleDAO dao = new AuxiliarDetalleDAO(emf);

        try {
            // Leer el archivo Excel
            Workbook workbook = new XSSFWorkbook(fileContent);
            Sheet sheet = workbook.getSheetAt(0); // Suponemos que el archivo tiene solo una hoja

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;  // Salta la fila de encabezados
                }

                try {
                    // Obtener y validar CodiAux (Celda 0)
                    Integer codiAux = null;
                    if (row.getCell(0) != null) {
                        if (row.getCell(0).getCellType() == CellType.NUMERIC) {
                            codiAux = (int) row.getCell(0).getNumericCellValue();
                        } else if (row.getCell(0).getCellType() == CellType.STRING) {
                            String codiAuxStr = row.getCell(0).getStringCellValue().trim();
                            if (!codiAuxStr.isEmpty()) {
                                codiAux = Integer.parseInt(codiAuxStr);
                            }
                        }
                    }

                    // Obtener y validar CodiPers (Celda 1)
                    Integer codiPers = null;
                    if (row.getCell(1) != null) {
                        if (row.getCell(1).getCellType() == CellType.NUMERIC) {
                            codiPers = (int) row.getCell(1).getNumericCellValue();
                        } else if (row.getCell(1).getCellType() == CellType.STRING) {
                            String codiPersStr = row.getCell(1).getStringCellValue().trim();
                            if (!codiPersStr.isEmpty()) {
                                codiPers = Integer.parseInt(codiPersStr);
                            }
                        }
                    }

                    // Obtener y validar DescDeta (Celda 2)
                    String descDeta = "";
                    if (row.getCell(2) != null && row.getCell(2).getCellType() == CellType.STRING) {
                        descDeta = row.getCell(2).getStringCellValue().trim();
                    }
                    if (descDeta.isEmpty()) {
                        descDeta = "Sin descripción"; // Valor por defecto si está vacío
                    }

                    // Obtener y validar MontDeta (Celda 3)
                    double montDetaDouble = 0;
                    if (row.getCell(3) != null) {
                        if (row.getCell(3).getCellType() == CellType.NUMERIC) {
                            montDetaDouble = row.getCell(3).getNumericCellValue();
                        } else if (row.getCell(3).getCellType() == CellType.STRING) {
                            String montDetaStr = row.getCell(3).getStringCellValue().trim();
                            if (!montDetaStr.isEmpty()) {
                                try {
                                    montDetaDouble = Double.parseDouble(montDetaStr);
                                } catch (NumberFormatException e) {
                                    montDetaDouble = 0; // Si no es un número válido, asignar 0
                                }
                            }
                        }
                    }

                    // Verificar si los datos son válidos antes de crear el objeto
                    if (codiAux != null && codiPers != null && !descDeta.isEmpty() && montDetaDouble > 0) {
                        BigDecimal montDeta = BigDecimal.valueOf(montDetaDouble);

                        // Crear un nuevo objeto AuxiliarDetalle
                        AuxiliarDetalle detalle = new AuxiliarDetalle();
                        detalle.setCodiAux(codiAux);
                        detalle.setCodiPers(codiPers);
                        detalle.setDescDeta(descDeta);
                        detalle.setMontDeta(montDeta);
                        System.out.println("Importando auxiliar: CodiAux=" + codiAux + ", CodiPers=" + codiPers + ", DescDeta=" + descDeta + ", MontDeta=" + montDeta);

                        // Usar el DAO para insertar el detalle
                        dao.create(detalle);  // Aquí estamos usando el método de tu DAO para persistir los datos
                    } else {
                        System.out.println("Error en los datos de la fila " + row.getRowNum() + ": Datos inválidos.");
                    }

                } catch (Exception e) {
                    System.out.println("Error al procesar la fila " + row.getRowNum() + ": " + e.getMessage());
                }
            }

            workbook.close();

            // Responder al cliente que la importación fue exitosa
            response.getWriter().write("Datos importados correctamente.");

        } catch (Exception e) {
            // En caso de error, respondemos con un error
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error al procesar el archivo.");
            e.printStackTrace();
        }
    }

}
