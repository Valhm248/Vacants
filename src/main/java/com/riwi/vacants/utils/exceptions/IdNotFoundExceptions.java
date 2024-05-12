package com.riwi.vacants.utils.exceptions;

//RuntimeException es la clase general de errores de java la utilizaremos para utilizar su constructor y generar errores
public class IdNotFoundExceptions extends RuntimeException {
    private static final String ERROR_MESSAGE = "No hay registros en la entidad %s con el id suministrado";

    //Utilizamos el constructor de runtimeexception y enviamos el mensaje inyectando el nombre de la entidad
    public IdNotFoundExceptions(String nameEntity){
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}