package ui;

import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.Controller;
import model.Priority;

public class Main {
    private Scanner reader;
    private Controller controller;

    public Main() {
        reader = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("===== Menú de Gestión de Tareas =====");
            System.out.println("1. Agregar Tarea");
            System.out.println("2. Modificar Tarea");
            System.out.println("3. Eliminar Tarea");
            System.out.println("4. Mostrar Tareas");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");

            int choice = validateInt();
            reader.nextLine(); // Consumir la nueva línea después de nextInt()

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    // showTasks();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    public void addTask() {
        System.out.println();
        System.out.print("Título: ");
        String title = reader.nextLine();

        System.out.print("Descripción: ");
        String description = reader.nextLine();

        System.out.print("Fecha Límite (yyyy-MM-dd): ");
        Date deadline = parseDate();

        System.out.print("¿Es prioritaria?:\n");
        System.out.println("1. Si");
        System.out.println("2. No");
        int option = validateInt();
        Priority priority = null;

        switch (option) {
            case 1:
                priority = Priority.PRIORITY;
                break;
            case 2:
                priority = Priority.NO_PRIORITY;
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        String msg = controller.addTask(title, description, deadline, priority);
        System.out.println(msg);
    }

    public void deleteTak() {
        System.out.println("Ingrese la clave de la tarea a eliminar");
        String idTask = reader.nextLine();
        String msg = controller.removeTask(idTask);
        System.out.println(msg);
    }

    public Date parseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        while (date == null) {
            String dateString = reader.nextLine();

            try {
                date = dateFormat.parse(dateString);
                if (date.before(new Date())) {
                    System.out.println("Error: La fecha límite no puede ser una fecha pasada.");
                    date = null; // Reiniciamos date para solicitar una nueva fecha
                }
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Por favor, use el formato yyyy-MM-dd.");
            }
        }

        return date;
    }

    public int validateInt() {
        int option = 0;
        do {
            if (reader.hasNextInt()) {
                option = reader.nextInt();
            } else {
                reader.next();// limpiar el scanner
                System.out.println("Invalid number!");
                System.out.print("Conrrently Type: ");
                option = Integer.MAX_VALUE;
            }
        } while (option == Integer.MAX_VALUE);
        return option;
    }

}