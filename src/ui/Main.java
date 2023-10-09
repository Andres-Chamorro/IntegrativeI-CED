package ui;

import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import model.ControllerTask;
import model.Priority;

public class Main {
    private Scanner reader;
    private ControllerTask controller;

    public Main() {
        reader = new Scanner(System.in);
        controller = new ControllerTask();
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
            reader.nextLine();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    modifyTask();
                    break;
                case 3:
                    deleteTak();
                    break;
                case 4:
                    listPriority();
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
        boolean idExist;
        do {
            System.out.println("ID de la tarea");
            String id = reader.nextLine();
            idExist = controller.searchTask(id);
            if (idExist) {
                System.out.println("El id ya existe, ingrese un id unico");
            } else {
                System.out.print("Título: ");
                String title = reader.nextLine();

                System.out.print("Descripción: ");
                String description = reader.nextLine();

                System.out.print("Fecha Límite (yyyy-MM-dd): ");
                Date deadline = parseDate();

                System.out.print("¿ Que tan prioritaria es?:\n");
                System.out.println("1. Alta");
                System.out.println("2. Media");
                System.out.println("3. Baja");
                int option = validateInt();
                Priority priority = null;

                switch (option) {
                    case 1:
                        priority = Priority.ALTA;
                        break;
                    case 2:
                        priority = Priority.MEDIA;
                        break;
                    case 3:
                        priority = Priority.BAJA;
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }

                String msg = controller.addTask(id, title, description, deadline, priority);
                System.out.println(msg);
            }

        } while (idExist);
    }

    public void deleteTak() {
        System.out.println("Ingrese la clave de la tarea a eliminar");
        String idTask = reader.nextLine();
        String msg = controller.removeTask(idTask);
        System.out.println(msg);
    }

    public void modifyTask() {
        System.out.print("Ingrese el ID de la tarea que desea modificar: ");
        String taskId = reader.nextLine();

        if (controller.searchTask(taskId)) {
            String newTitle = "";
            String newDescription = "";
            Date newDeadline = null;
            Priority newPriority = null;
            int modifyChoice;

            do {
                System.out.println("===== Menú de Modificación de Tarea =====");
                System.out.println("1. Modificar Título");
                System.out.println("2. Modificar Descripción");
                System.out.println("3. Modificar Fecha Límite");
                System.out.println("4. Modificar Prioridad");
                System.out.println("0. Volver al menu principal");
                System.out.print("Elija una opción: ");

                modifyChoice = validateInt();
                reader.nextLine(); // Consumir la nueva línea después de nextInt()

                switch (modifyChoice) {
                    case 1:
                        System.out.print("Nuevo Título: ");
                        newTitle = reader.nextLine();
                        break;
                    case 2:
                        System.out.print("Nueva Descripción: ");
                        newDescription = reader.nextLine();
                        break;
                    case 3:
                        System.out.print("Nueva Fecha Límite (yyyy-MM-dd): ");
                        Date deadline = parseDate();
                        break;
                    case 4:
                        System.out.print("Nueva Prioridad:\n");
                        System.out.println("1. Alta");
                        System.out.println("2. Media");
                        System.out.println("3. Baja");
                        int option = validateInt();

                        switch (option) {
                            case 1:
                                newPriority = Priority.ALTA;
                                break;
                            case 2:
                                newPriority = Priority.MEDIA;
                                break;
                            case 3:
                                newPriority = Priority.BAJA;
                                break;
                            default:
                                System.out.println("Opción no válida");
                                break;
                        }
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (modifyChoice != 0);

            String modifyMessage = controller.modifyTask(taskId, newTitle, newDescription, newDeadline, newPriority);
            System.out.println(modifyMessage);
        } else {
            System.out.println("La tarea con el ID " + taskId + " no existe.");
        }
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

    public void listPriority() {
        controller.printListByPriority();
    }

}