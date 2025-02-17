/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package cu;

/**
 *
 * @author USER
 */
public enum Categoria {

    SISTEMA(1, "SISTEMA"),
    INGRESOS(2, "INGRESOS"),
    EGRESOS(3, "EGRESOS"),
    APORTES(3, "APORTES"),
    TOTAL(3, "TOTAL");

    private final int id;
    private final String name;

    // Constructor privado para inicializar las constantes
    private Categoria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getValor() {
        return id;
    }
}
