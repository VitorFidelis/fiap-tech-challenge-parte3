package com.agendio_api.agendamento.api.routes;

public class RotasPaciente {
    private RotasPaciente() {}

    public static final String PACIENTE = "/pacientes";
    public static final String ID = "/{id}";
    public static final String REATIVAR = "/reativar";
    public static final String DESATIVAR = "/desativar";
    public static final String TODOS = "/todos";
    public static final String PACIENTE_COM_ID = PACIENTE + ID;
    public static final String ID_E_REATIVAR = ID + REATIVAR;
    public static final String ID_E_DESATIVAR = ID + DESATIVAR;
}
