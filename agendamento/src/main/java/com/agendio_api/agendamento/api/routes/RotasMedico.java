package com.agendio_api.agendamento.api.routes;

public class RotasMedico {
    private RotasMedico() {}


    public static final String MEDICO = "/medicos";
    public static final String ID = "/{id}";
    public static final String CRM = "/crm/{crm}";
    public static final String ESPECIALIDADE = "/especialidade/{especialidade}";
    public static final String REATIVAR = "/reativar";
    public static final String DESATIVAR = "/desativar";
    public static final String TODOS = "/todos";
    public static final String MEDICO_COM_ID = MEDICO + ID;
    public static final String ID_E_REATIVAR = ID + REATIVAR;
    public static final String ID_E_DESATIVAR = ID + DESATIVAR;
}
