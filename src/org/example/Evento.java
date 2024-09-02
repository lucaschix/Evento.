package org.example;


import java.util.Scanner;

public class Evento {
    public final String[][] matriz;
    private int aforoVip;
    private int aforoGeneral;

    public static void main(String[] args) {
        Evento evento = new Evento(10, 20);
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("1. Agregar persona");
            System.out.println("2. Verificar edad");
            System.out.println("3. Verificar boleto");
            System.out.println("4. Validar invitados");
            System.out.println("5. Verificar aforo disponible");
            System.out.println("6. Permitir entrada");
            System.out.println("7. Remover persona");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    agregarPersona(evento, scanner);
                    break;
                case 2:
                    verificarEdad(evento, scanner);
                    break;
                case 3:
                    verificarBoleto(evento, scanner);
                    break;
                case 4:
                    validarInvitados(evento, scanner);
                    break;
                case 5:
                    verificarAforoDisponible(evento, scanner);
                    break;
                case 6:
                    permitirEntrada(evento, scanner);
                    break;
                case 7:
                    removerPersona(evento, scanner);
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 1 y 9.");
            }
        } while (opcion != 8);
    }

    public  Evento(int aforoVip, int aforoGeneral) {
        this.aforoVip = aforoVip;
        this.aforoGeneral = aforoGeneral;
        this.matriz = new String[10][5];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                this.matriz[i][j] = "";
            }
        }
    }
    public void agregarPersona(int fila, String nombre, int edad, String entrada, int invitados) {
        this.matriz[fila][0] = nombre;
        this.matriz[fila][1] = String.valueOf(edad);
        this.matriz[fila][2] = entrada;
        this.matriz[fila][3] = String.valueOf(invitados);
        this.matriz[fila][4] = "False";
    }

    public boolean verificarEdad(int fila) {
        int edad = Integer.parseInt(this.matriz[fila][1]);
        if (edad >= 18) {
            System.out.println(" es mayor de edad y puede entrar.");
            return true;
        } else {
            System.out.println(" es menor de edad y tiene el acceso prohibido.");
            return false;
        }
    }
    public String verificarBoleto(int fila) {
        return this.matriz[fila][2];
    }
    public boolean validarInvitados(int fila) {
        int invitados = Integer.parseInt(this.matriz[fila][3]);
        String entrada = this.matriz[fila][2];
        if (entrada.equals("VIP")) {
            return true; // Si tiene entrada VIP, no hay límite de invitados
        } else {
            if (invitados == 0) {
                return true; // Si no tiene entrada VIP y no tiene invitados, está bien
            } else {
                System.out.println("Error: La persona sin entrada VIP no puede llevar invitados.");
                return false; // Si no tiene entrada VIP y tiene invitados, error
            }
        }
    }
    public int aforoDisponible(String sala) {
        int aforo = 0;
        if (sala.equals("VIP")) {
            aforo = this.aforoVip;
            for (int i = 0; i < 10; i++) {
                if (this.matriz[i][2].equals("VIP") && this.matriz[i][4].equals("True")) {
                    aforo -= Integer.parseInt(this.matriz[i][3]) + 1;
                }
            }
        } else if (sala.equals("General")) {
            aforo = this.aforoGeneral;
            for (int i = 0; i < 10; i++) {
                if (this.matriz[i][2].equals("General") && this.matriz[i][4].equals("True")) {
                    aforo -= 1;
                }
            }
        }
        return aforo;
    }
    public void ingresarPersona(int fila) {
        this.matriz[fila][4] = "True";
    }
    public boolean permitirEntrada(int fila) {
        if (this.verificarEdad(fila) && (this.verificarBoleto(fila).equals("VIP") || this.verificarBoleto(fila).equals("General"))) {
            if (this.verificarBoleto(fila).equals("VIP")) {
                if (this.aforoDisponible("VIP") >= Integer.parseInt(this.matriz[fila][3]) + 1) {
                    this.ingresarPersona(fila);
                    return true;
                }
            } else if (this.verificarBoleto(fila).equals("General")) {
                if (this.aforoDisponible("General") >= 1) {
                    this.ingresarPersona(fila);
                    return true;
                }
            }
        }
        return false;
    }


    public void removerPersona(int fila) {
        if (this.matriz[fila][2].equals("VIP")) {
            this.aforoVip += Integer.parseInt(this.matriz[fila][3]) + 1;
        } else if (this.matriz[fila][2].equals("General")) {
            this.aforoGeneral += 1;
        }
        this.matriz[fila][4] = "False";
    }



    private static void agregarPersona(Evento evento, Scanner scanner) {
        System.out.print("Ingrese el nombre de la persona: ");
        String nombre = scanner.next();
        System.out.print("Ingrese la edad de la persona: ");
        int edad = scanner.nextInt();
        System.out.print("Ingrese el tipo de entrada (VIP o General): ");
        String entrada = scanner.next();
        System.out.print("Ingrese el número de invitados: ");
        int invitados = scanner.nextInt();
        evento.agregarPersona(0, nombre, edad, entrada, invitados);
    }

    private static void verificarEdad(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        System.out.print("Ingrese el nombre de la persona: ");
        evento.verificarEdad(fila);
    }

    private static void verificarBoleto(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        System.out.println(evento.verificarBoleto(fila));
    }

    private static void validarInvitados(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        if (evento.validarInvitados(fila)) {
            System.out.println("Invitados válidos");
        } else {
            System.out.println("Error: Invitados no válidos");
        }
    }
    private static void verificarAforoDisponible(Evento evento, Scanner scanner) {
        System.out.print("Ingrese el tipo de sala (VIP o General): ");
        String sala = scanner.next();
        System.out.println(evento.aforoDisponible(sala));
    }

    private static void ingresarPersona(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        evento.ingresarPersona(fila);
    }

    private static void permitirEntrada(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        if (evento.permitirEntrada(fila)) {
            System.out.println("Entrada permitida");
        } else {
            System.out.println("Entrada no permitida");
        }
    }

    private static void removerPersona(Evento evento, Scanner scanner) {
        System.out.print("Ingrese la fila de la persona: ");
        int fila = scanner.nextInt();
        evento.removerPersona(fila);
    }}