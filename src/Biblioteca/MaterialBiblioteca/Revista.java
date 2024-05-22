package Biblioteca.MaterialBiblioteca;

public class Revista extends Libreria{

    private String editor;
    private int numDeEdicion;

    public Revista(String titulo, String editor, int numDeEdicion) {
        super(titulo);
        this.editor = editor;
        this.numDeEdicion = numDeEdicion;
        this.id = (this.editor.length() >= 3)? (editor.substring(0,3)) : ((this.editor.length() == 2)? "-" + editor : "--" + editor);
    }

    @Override
    public void actualizarRevista() {
        setNumDeEdicion(numDeEdicion+1);
    }

    @Override
    public String toString() {
        return super.toString() + " | Editor: " + editor + " | Numero de edición: " + numDeEdicion + " | Estado: " + ((estado)?"Prestado":"No prestado");
    }

    public int getNumDeEdicion() {
        return numDeEdicion;
    }

    public void setNumDeEdicion(int numDeEdicion) {
        this.numDeEdicion = numDeEdicion;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    

}
