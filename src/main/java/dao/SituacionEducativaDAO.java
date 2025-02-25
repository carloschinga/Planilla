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
public class SituacionEducativaDAO extends SituacionEducativaJpaController{
    
    public SituacionEducativaDAO(EntityManagerFactory emf) {
        super(emf);
    }
    
}
