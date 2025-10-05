package com.agendio_api.agendamento.api.routes;

public class RotasEnfermeiro {
    private RotasEnfermeiro() {}

    public static final String ENFERMEIRO = "/enfermeiros";
    public static final String ID = "/{id}";
    public static final String REATIVAR = "/reativar";
    public static final String DESATIVAR = "/desativar";
    public static final String TODOS = "/todos";
    public static final String ENFERMEIRO_COM_ID = ENFERMEIRO + ID;
    public static final String ID_E_REATIVAR = ID + REATIVAR;
    public static final String ID_E_DESATIVAR = ID + DESATIVAR;
}
