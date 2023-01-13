package com.ronal.blog.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constantes {

    /*
    *CONSTANTES PARA EL PAGINADO DE PUBLICATION
     */
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY= "idPublication";
    public static final String SORT_DIR = "asc";


    /*
    *CONSTANTES DE BAD REQUEST PUBLICATION
    */
    public static final String BAD_REQUEST_TITLE = "Title is empty or null";
    public static final String BAD_REQUEST_DESCRIPTION = "Description is empty or null";
    public static final String BAD_REQUEST_CONTENT = "Content is empty or null";
}