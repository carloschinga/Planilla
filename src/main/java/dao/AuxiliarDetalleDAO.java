/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.AuxiliarDetalle;
import dto.VistaAuxiliarDetallePersona;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class AuxiliarDetalleDAO extends AuxiliarDetalleJpaController {

    public AuxiliarDetalleDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<VistaAuxiliarDetallePersona> findAuxiliarByPeriodo(Integer auxiliarId) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery("SELECT v FROM VistaAuxiliarDetallePersona v WHERE v.codiAux = :auxiliar", VistaAuxiliarDetallePersona.class)
                    .setParameter("auxiliar", auxiliarId)
                    .getResultList();

        } finally {
            em.close();
        }
    }
    // Método para importar los datos desde la lista de AuxiliarDetalle

    public void importarDatos(List<AuxiliarDetalle> auxiliarDetalles) {
        EntityManager em = getEntityManager();
        try {
            // Comenzar la transacción
            em.getTransaction().begin();

            // Recorrer la lista y persistir cada objeto en la base de datos
            for (AuxiliarDetalle detalle : auxiliarDetalles) {
                em.persist(detalle);
            }

            // Confirmar la transacción
            em.getTransaction().commit();

        } catch (Exception e) {
            // Si ocurre un error, revertir la transacción
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();  // Imprimir el error
        } finally {
            // Cerrar el EntityManager después de la operación
            em.close();
        }
    }
}
