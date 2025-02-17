/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.VistaUsuarioRol;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class VistaUsuarioRolDAO extends VistaUsuarioRolJpaController{
    
    public VistaUsuarioRolDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
    public VistaUsuarioRol validarUsuario(String logiUsua, String passUsua) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("VistaUsuarioRol.validar", VistaUsuarioRol.class);
            q.setParameter("logiUsua", logiUsua);
            q.setParameter("passUsua", passUsua);
            List<VistaUsuarioRol> lista = q.getResultList();
            return lista.get(0);
        } catch (Exception ex) {
            String mensaje= ex.getMessage();
            return null;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
    
        VistaUsuarioRolDAO vurDAO= new VistaUsuarioRolDAO(emf);
        VistaUsuarioRol vur=vurDAO.validarUsuario("kike", "1234");
        System.out.println(vur.getLogiUsua());
    }
    
}
