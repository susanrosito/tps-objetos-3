package metaprogramacion;

public class Example {
	
        private String autor;
        private String titulo;
        private Cliente cliente;

        public Example(String autor, String titulo) {
                this.autor = autor;
                this.titulo = titulo;
        }
 
        public String getAutor() {
                return this.autor;
        }
 
        public String getTitulo() {
                return this.titulo;
        }
 
        public Cliente getCliente() {
                return this.cliente;
        }
 
        public void prestarA(Cliente cliente) throws LibreriaException {
                if (this.cliente != null) {
                        throw new LibreriaException("Libro ya prestado a " + this.cliente.getNombre());
                }
                this.cliente = cliente;
        }
}
