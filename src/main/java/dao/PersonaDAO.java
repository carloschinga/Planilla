/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Persona;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class PersonaDAO extends PersonaJpaController {

    public PersonaDAO(EntityManagerFactory emf) {
        super(emf);
    }

    public List<Persona> listarActivos() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Persona.findByActiPers");
            q.setParameter("actiPers", true);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Map<Integer, BigDecimal> getCodiPersAndSuelPers() {
        EntityManager em = getEntityManager();
        try {
            // Obtener todas las personas
            List<Persona> personas = listarActivos();
            Map<Integer, BigDecimal> result = new HashMap<>();

            // Llenar la tabla hash con codiPers y suelPers
            for (Persona persona : personas) {
                result.put(persona.getCodiPers(), persona.getSuelPers());
            }

            return result;
        } finally {
            em.close();
        }
    }
    public static void main(String[] args) {
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");

        PersonaDAO personaDAO= new PersonaDAO(emf);
        List<Persona> lista=personaDAO.listarActivos();
        for (Persona persona : lista) {
             System.out.println(persona.getCodiPlant());
        }
       
    }
}
