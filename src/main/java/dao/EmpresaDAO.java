/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.Empresa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author USER
 */
public class EmpresaDAO extends EmpresaJpaController{
    
    public EmpresaDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
    public List<Empresa> listaEmpresaActivas() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNamedQuery("Empresa.findByDefaEmpr");
            q.setParameter("defaEmpr", true);
            return q.getResultList();
        } catch(Exception ex){
        return null;}
            finally {
            em.close();
        }
    }
     public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Planilla_war_1.0-SNAPSHOTPU");
    
        EmpresaDAO empresaDAO= new EmpresaDAO(emf);
        List<Empresa> lista=empresaDAO.listaEmpresaActivas();
         for (Empresa empresa : lista) {
             System.out.println(empresa.getNombEmpr());
         }
    }

}
