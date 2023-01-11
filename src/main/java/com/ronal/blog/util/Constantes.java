package com.ronal.blog.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constantes {
    public static final String NUMERO_DE_PAGINA_POR_DEFECTO = "0";
    public static final String MEDIDA_DE_PAGINA_POR_DEFECTO = "10";
    public static final String ORDENAR_POR_DEFECTO = "id";
    public static final String ORDENAR_DIRECCION_POR_DEFECTO = "asc";


    /*CONSTANTES DE BAD REQUEST PUBLICATION*/
    public static final String BAD_REQUEST_TITLE = "Title is empty or null";
    public static final String BAD_REQUEST_DESCRIPTION = "Description is empty or null";
    public static final String BAD_REQUEST_CONTENT = "Content is empty or null";
}