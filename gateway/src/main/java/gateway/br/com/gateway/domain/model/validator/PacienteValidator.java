package gateway.br.com.gateway.domain.model.validator;


import gateway.br.com.gateway.domain.model.usuario.Paciente;

public interface PacienteValidator extends UsuarioValidator {
    void validar(Paciente paciente);
}
