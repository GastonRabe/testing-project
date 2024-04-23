package sistema.personas.medicos;

public interface IMedico {
    String getNombre();

    String getApellido();

    double getSueldo();

    String getDescripcion();

    int getMatricula();

    void setMatricula(int matricula);
}
