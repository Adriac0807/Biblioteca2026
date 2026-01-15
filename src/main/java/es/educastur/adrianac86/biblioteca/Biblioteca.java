/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package es.educastur.adrianac86.biblioteca;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author 1dawd11
 */
public class Biblioteca {

    private static ArrayList<Libro> libros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Prestamo> prestamos = new ArrayList<>();
    private static final ArrayList<Prestamo> prestamosHist = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        /*cargaDatos();
        menuPrincipal();
        histUsuario();*/

        cargaDatosPrueba12();
        //uno();
        //dos();
        tres();
        cuatro();
        cinco();
    }

    public static void uno() {
        System.out.print("\nTeclea el ISBN:");
        String isbn = sc.next();
        int pos = buscaLibro(isbn);
        if (pos == -1) {
            System.out.println("Ese libro no existe");
        } else {
            System.out.println("Prestamos activos del libro " + libros.get(pos).getTitulo() + ": ");
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equalsIgnoreCase(isbn)) {
                    System.out.println(libros.get(pos).getTitulo() + "-" + p.getUsuarioPrest());
                }
            }
        }
    }

    public static void tres() {
        System.out.println("Usuarios con al menos 1 prestamo ACTIVO:");
        for (Prestamo p : prestamos) {
            if (!p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p.getUsuarioPrest());
            }
        }
    }
    
    public static void cuatro() {
        System.out.println("Usuarios con prestamos fuera de plazo:");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p.getUsuarioPrest());
            }
        }
    }
    public static void cinco() {
        System.out.println("Prestamos realizados en Noviembre:");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().getMonth() == (Month.NOVEMBER)) {
                System.out.println(p);
            }
        }
    }

    public static void cargaDatosPrueba12() {
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Aventuras", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Aventuras", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()

        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(30), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(15)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(10), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(10), hoy));
    }

    /*public static void cargaDatos() {
        libros.add(new Libro("1-11", "El Hobbit", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-22", "El Silmarillon", "JRR Tolkien", "Aventuras", 3));
        libros.add(new Libro("1-33", "El Medico", "N. Gordon", "Aventuras", 4));
        libros.add(new Libro("1-44", "Chaman", "N. Gordon", "Aventuras", 3));
        libros.add(new Libro("1-55", "Momo", "M. Ende", "Aventuras", 2));
        libros.add(new Libro("1-66", "Paraiso inhabitado", "A.M.Matute", "Aventuras", 2));
        libros.add(new Libro("1-77", "Olvidado Rey Gudu", "A.M.Matute", "Aventuras", 0));
        libros.add(new Libro("1-88", "El ultimo barco", "D.Villar", "Novela Negra", 3));
        libros.add(new Libro("1-99", "Ojos de agua", "D.Villar", "Novela Negra", 0));

        usuarios.add(new Usuario("11", "Ana", "ana@email.com", "621111111"));
        usuarios.add(new Usuario("22", "David", "david@email.com", "622222222"));
        usuarios.add(new Usuario("33", "Bea", "bea@email.com", "623333333"));
        usuarios.add(new Usuario("44", "Lucas", "lucas@email.com", "624444444"));
        usuarios.add(new Usuario("55", "Carlota", "carlota@email.com", "625555555"));
        usuarios.add(new Usuario("66", "Juan", "juan@email.com", "626666666"));

        LocalDate hoy = LocalDate.now(); //OBTENEMOS LA FECHA DE HOY CON EL MÉTODO now()
        //PRESTAMOS "NORMALES" REALIZADOS HOY Y QUE SE HAN DE DEVOLVER EN 15 DÍAS
        prestamos.add(new Prestamo(libros.get(0), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(0), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(4), hoy, hoy.plusDays(15)));
        prestamos.add(new Prestamo(libros.get(6), usuarios.get(1), hoy, hoy.plusDays(15)));
        //PRESTAMOS QUE YA TENIAN QUE HABER SIDO DEVUELTOS PORQUE SU FECHA DE DEVOLUCIÓN ES ANTERIOR A HOY
        prestamos.add(new Prestamo(libros.get(5), usuarios.get(1), hoy.minusDays(17), hoy.minusDays(2)));
        prestamos.add(new Prestamo(libros.get(1), usuarios.get(4), hoy.minusDays(18), hoy.minusDays(3)));
        prestamos.add(new Prestamo(libros.get(2), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamos.add(new Prestamo(libros.get(3), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));

        //PRESTAMOS HISTORICOS QUE YA HAN SIDO DEVUELTOS Y POR TANTO ESTÁN EN LA COLECCION prestamosHist
        prestamosHist.add(new Prestamo(libros.get(0), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(2), usuarios.get(0), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(5), usuarios.get(4), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(1), usuarios.get(1), hoy.minusDays(20), hoy.minusDays(5)));
        prestamosHist.add(new Prestamo(libros.get(7), usuarios.get(2), hoy.minusDays(15), hoy));
        prestamosHist.add(new Prestamo(libros.get(6), usuarios.get(3), hoy.minusDays(15), hoy));
    }*/
    public static void menuPrincipal() {

        int opcion;

        do {
            System.out.println("\n--MENU PRINCIPAL--");
            System.out.println("1. Gestion de Libros");
            System.out.println("2. Gestion de usuarios");
            System.out.println("3. Gestion de prestamos");
            System.out.println("4. Listados");
            System.out.println("9. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuLibros();
                    break;
                case 2:
                    menuUsuarios();
                    break;
                case 3:
                    gestionPrestamos();
                    break;
                case 4:
                    listarColecciones();
                    break;
            }
        } while (opcion != 9);

    }

    public static void menuLibros() {
        int opcion;

        do {
            System.out.println("\n--MENU LIBROS--");
            System.out.println("1. Agregar Libros");
            System.out.println("2. Listar Libros");
            System.out.println("3. Modificar Libros");
            System.out.println("4. Eliminar Libros");
            System.out.println("9. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarLibros();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    modificarLibro();
                    break;
                case 4:
                    borrarLibro();
                    break;
            }
        } while (opcion != 9);
    }

    public static void agregarLibros() {
        System.out.println("ISBN: ");
        String isbn = sc.nextLine();
        System.out.println("Titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        System.out.println("Genero: ");
        String genero = sc.nextLine();
        System.out.println("Ejemplares: ");
        int ejemplares = sc.nextInt();
        libros.add(new Libro(isbn, titulo, autor, genero, ejemplares));

    }

    public static void listarLibros() {
        for (Libro l : libros) {
            System.out.println(l);
        }
    }

    public static int buscaLibro(String isbn) {
        int pos = -1;
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getIsbn().equals(isbn)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static void borrarLibro() {
        System.out.print("\nTeclea el ISBN del libro a borrar:");
        String isbn = sc.next();
        int p = buscaLibro(isbn);
        if (p != -1) {
            libros.remove(p);
            System.out.println("Libro eliminado");
        } else {
            System.out.println("El libro no existe");
        }
    }

    public static void modificarLibro() {
        System.out.print("\nTeclea el nombre del libro a modificar:");
        String isbn = sc.next();
        int p = buscaLibro(isbn);
        if (p == -1) {
            System.out.println("Ese libro no existe");
        } else {
            System.out.print("\n\nNuevo ISBN: ");
            isbn = sc.next();
            System.out.print("Nuevo Titulo: ");
            String titulo = sc.next();
            System.out.print("Nuevo Autor: ");
            String autor = sc.next();
            System.out.print("Nuevo Genero: ");
            String genero = sc.next();
            System.out.print("Numero de ejemplares:");
            int ejemplares = sc.nextInt();
            libros.get(p).setIsbn(isbn);
            libros.get(p).setTitulo(titulo);
            libros.get(p).setAutor(autor);
            libros.get(p).setGenero(genero);
            libros.get(p).setEjemplares(ejemplares);

        }

    }

    public static void menuUsuarios() {
        int opcion;

        do {
            System.out.println("\n--MENU USUARIOS--");
            System.out.println("1. Agregar Usuarios");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Modificar Usuarios");
            System.out.println("4. Eliminar Usuarios");
            System.out.println("9. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    agregarUsuarios();
                    break;
                case 2:
                    listarUsuarios();
                    break;
                case 3:
                    modificarUsuarios();
                    break;
                case 4:
                    borrarUsuarios();
                    break;
            }
        } while (opcion != 9);
    }

    public static int buscaDni(String dni) {
        int pos = -1;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getDni().equals(dni)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static void agregarUsuarios() {
        System.out.println("DNI: ");
        String dni = sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Telefono: ");
        String telefono = sc.nextLine();
        usuarios.add(new Usuario(dni, nombre, email, telefono));
    }

    public static void listarUsuarios() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    public static void modificarUsuarios() {
        System.out.print("\nTeclea el DNI del usuario a modificar:");
        String dni = sc.next();
        int p = buscaDni(dni);
        if (p == -1) {
            System.out.println("Ese usuario no existe");
        } else {
            System.out.print("Nuevo nombre: ");
            String nombre = sc.next();
            System.out.print("Nuevo email: ");
            String email = sc.next();
            System.out.print("Nuevo telefono: ");
            String telefono = sc.next();
            usuarios.get(p).setDni(dni);
            usuarios.get(p).setNombre(nombre);
            usuarios.get(p).setEmail(email);
            usuarios.get(p).setTelefono(telefono);

        }

    }

    public static void borrarUsuarios() {
        System.out.print("\nTeclea el DNI del usuario a borrar:");
        String dni = sc.next();
        int p = buscaDni(dni);
        if (p != -1) {
            usuarios.remove(p);
            System.out.println("Usuario eliminado");
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public static void gestionPrestamos() {
        int opcion;

        do {
            System.out.println("\n--MENU LIBROS--");
            System.out.println("1. Nuevo Prestamo");
            System.out.println("2. Prorroga");
            System.out.println("3. Devolucion");
            System.out.println("4. Listar Prestamos");
            System.out.println("9. Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    nuevoPrestamos();
                    break;
                case 2:
                    prorroga();
                    break;
                case 3:
                    devolucion();
                    break;
                case 4:
                    listarPrestamos();
                    break;
            }
        } while (opcion != 9);
    }

    public static int buscaPrestamo(String dni, String isbn) {
        int pos = -1;
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getUsuarioPrest().getDni().equals(dni)
                    && prestamos.get(i).getLibroPrest().getIsbn().equals(isbn)) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public static void nuevoPrestamos() {
        String dni, isbn;
        int posUsuario, posLibro;
        System.out.println("Teclea el DNI del usuario: ");
        dni = sc.next();
        posUsuario = buscaDni(dni);
        if (posUsuario == -1) {
            System.out.println("Ese usuario no existe");
        } else {
            System.out.println("Tecela ISBN del libro: ");
            isbn = sc.next();
            posLibro = buscaLibro(isbn);
            System.out.println("Fecha del PRESTAMO: ");
            LocalDate hoy = LocalDate.now();
            System.out.println(hoy);
            LocalDate devolucion = hoy.plusDays(15);
            System.out.println("Fecha de DEVOLUCION: " + "\n" + devolucion);
            prestamos.add(new Prestamo(libros.get(posLibro), usuarios.get(posUsuario), hoy, devolucion));

//DEBEMOS RESTAR LOS EJEMPLARES DE LOS LIBROS QUE TENEMOS DISPONIBLES DESPÚES DEL PRÉSTAMO
            libros.get(posLibro).setEjemplares(libros.get(posLibro).getEjemplares() - 1);//Usamos SET.EJEMPLARES para restar la cantidad de ejemplares disponibles del libro
            System.out.println("Prestamo realizado y grabado en el ArrayList");
        }
    }

    private static void prorroga() {
        System.out.print("DNI usuario: ");
        String dni = sc.next();
        System.out.print("ISBN Libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningun préstamo con esos datos");
        } else {
            prestamos.get(posPrestamo).setFechaDev(prestamos.get(posPrestamo).getFechaDev().plusDays(15));
            System.out.println("Se amplió el plazo del préstamo 15 días");
        }
    }

    private static void devolucion() {
        System.out.print("DNI usuario: ");
        String dni = sc.next();
        System.out.print("ISBN Libro: ");
        String isbn = sc.next();
        int posPrestamo = buscaPrestamo(dni, isbn);
        if (posPrestamo == -1) {
            System.out.println("No hay ningun préstamo con esos datos");
        } else {
            prestamos.get(posPrestamo).setFechaDev(LocalDate.now());
            libros.get(buscaLibro(isbn))
                    .setEjemplares(libros.get(buscaLibro(isbn)).getEjemplares() + 1);
            prestamosHist.add(prestamos.get(posPrestamo));
            prestamos.remove(posPrestamo);
        }
    }

    private static void listarPrestamos() {
        System.out.println("\nVamos a listar los PRESTAMOS: ");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    /*
    private static void listaPrestamos() {
        System.out.println("Préstamos fuera de plazo");
        for (Prestamo p : prestamos) {
            if (p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println("Libro fuera de plazo:");
                System.out.println(p);
            }
        }
        System.out.println("Préstamos activos y en plazo:");
        for (Prestamo p : prestamos) {
            if (!p.getFechaDev().isBefore(LocalDate.now())) {
                System.out.println(p);
            }
        }

        System.out.println("Listado Historico");
        for (Prestamo p : prestamosHist) {
            System.out.println(p);
        }

    } */
    public static void listarColecciones() {
        System.out.println("Vamos a mostrar todos los libros de la biblioteca: ");
        for (Libro l : libros) {
            System.out.println(l);
//System.out.print("\n" + l.getIsbn() + "/" + l.getTitulo() + "/" + l.getAutor() + "/" + l.getGenero());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los usuarios de la biblioteca: ");
        for (Usuario u : usuarios) {
            System.out.println(u);
//System.out.print("\n" + u.getDni() + "/" + u.getNombre() + "/" + u.getEmail() + "/" + u.getTelefono());
        }
        System.out.println("");

        System.out.println("Vamos a mostrar los prestamos de la biblioteca: ");
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    public static void histUsuario() {
        System.out.print("\nTeclea el DNI del usuario para ver su historial de prestamos:");
        String dni = sc.next();
        int pos = buscaDni(dni);
        if (pos == -1) {
            System.out.println("Ese usuario no existe");
        } else {

            System.out.println("Prestamos activos de: " + usuarios.get(pos).getNombre());
            int cuentaActivos = 0;
            for (Prestamo p : prestamos) {
                if (p.getUsuarioPrest().getDni().equalsIgnoreCase(dni)) {
                    System.out.println(p);
                    cuentaActivos++;
                }
            }
            System.out.println(usuarios.get(pos).getNombre() + " tiene " + cuentaActivos + " prestamos actualmente.");

            System.out.println("Prestamos historicos de: " + usuarios.get(pos).getNombre());
            int cuentaHist = 0;
            for (Prestamo p : prestamosHist) {
                if (p.getUsuarioPrest().getDni().equalsIgnoreCase(dni)) {
                    System.out.println(p);
                    cuentaHist++;
                }
            }
            System.out.println(usuarios.get(pos).getNombre() + " tiene " + cuentaHist + " prestamos historicamente");
            int totalpres = cuentaActivos + cuentaHist;
            System.out.println(usuarios.get(pos).getNombre() + " tiene " + totalpres + " prestamos");
        }
    }
    
    public static int stockLibro(String isbn) throws LibroNoExiste, LibroNoDisponible {
        int pos = buscaLibro(isbn);
        if (pos == -1) {
            throw new LibroNoExiste("No existe en esta biblioteca la referencia: " + isbn);
        } else if (libros.get(pos).getEjemplares() == 0) {
            String cadena = "No hay unidades del libro " + libros.get(pos).getTitulo()
                    + " disponibles actualmente"
                    + "\nFechas de devolución previstas: ";
            for (Prestamo p : prestamos) {
                if (p.getLibroPrest().getIsbn().equals(isbn)) {
                    cadena = cadena + "\n * " + p.getFechaDev();
                }
            }
            throw new LibroNoDisponible(cadena);
        }
        return pos;
    }
    
      /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
   public static boolean esInt(String s) {
        int n;
        try{
            n=Integer.parseInt(s);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    
    public static boolean esDouble(String s) {
        double n;
        try {
            n = Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false; 
        }
    }
    
    /* EJEMPLOS DE UTILIZACION esInt() y esDouble() :
        
        //INTRODUCIR LAS EXISTENCIAS DE UN LIBRO EN UNA VARIABLE DE TIPO INT
        String exT;
	do {          
            System.out.println("EXISTENCIAS:");
            exT=sc.next(); //Se lee la entrada de EXISTENCIAS como un String
        } while(!esInt(exT)); //Se sigue pidiendo la entrada si no es int
        
        // INTRODUCIR EL PVP DE UN ARTÍCULO EN UNA VARIABLE DE TIPO DOUBLE
        String pvpT;
	do {          
            System.out.println("PVP:");
            pvpT=sc.next(); //Se lee la entrada del PVP como un String
        } while(!esDouble(pvpT)); /
    */
    
    
    
    public static boolean validarDNI(String dni) {
        // Verificar que el DNI tiene un formato válido
        if (dni.isBlank() || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }

        // Extraer el número y la letra del DNI
        String numeroStr = dni.substring(0, 8);
        char letra = dni.charAt(8);

        // Calcular la letra correspondiente al número del DNI
        char letraCalculada = calcularLetraDNI(Integer.parseInt(numeroStr));

        // Comparar la letra calculada con la letra proporcionada y devolver
        // el resultado de la comparación TRUE/FALSE
                      
        return letra == letraCalculada; 
        
        /* devuelve TRUE si la letra del DNI y la calculada según la fórmula COINCIDEN 
        SE PUEDE DEVOLVER ASI EL RESULTADO DE UNA COMPARACIÓN. Se devuelve TRUE si la comparación
        se cumple y FALSE sino. Es equivalente a poner:
          if (letra == letraCalculada) {
              return TRUE;
           } else {
              return FALSE;   
           }
        */
    }

    private static char calcularLetraDNI(int numero) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        return letras.charAt(numero % 23);
    } 
    
    
    /* EJEMPLO DE UTILIZACIÓN validarDNI()
        
        do{
            System.out.println("DNI CLIENTE:");
            dniT=sc.nextLine().toUpperCase();
        }while (!validarDNI(dniT));
    /*

}
