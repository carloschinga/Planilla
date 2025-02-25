/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cu;

/**
 *
 * @author USER
 */
public enum Tipo {

    VALOR(1, "VALOR"),
    FORMULA(2, "FORMULA");

    private final int id;
    private final String name;

    // Constructor privado para inicializar las constantes
    private Tipo(int id, String name) {
        this.id = id;
        this.name = name;
    }

   
    public int getValor() {
        return id;
    }

}
