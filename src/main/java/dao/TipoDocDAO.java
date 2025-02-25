/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author USER
 */
public class TipoDocDAO extends TipoDocJpaController{
    
    public TipoDocDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
}
