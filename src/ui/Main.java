package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import java.awt.*;

import model.ControllerTask;

public class Main {
    private ControllerTask controller;

    public Main() {
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
            System.out.println("5 Deshacer accion. ");
            System.out.println("6. Salir");
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
                    undoMethod();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
      
        JFrame frame = new JFrame("Menú de Gestión de Tareas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 220);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        JButton addTaskButton = new JButton("Agregar Tarea");
        JButton modifyTaskButton = new JButton("Modificar Tarea");
        JButton deleteTaskButton = new JButton("Eliminar Tarea");
        JButton listPriorityButton = new JButton("Mostrar Tareas");
        JButton exitButton = new JButton("Salir");

        addCenteredButton(panel, addTaskButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addCenteredButton(panel, modifyTaskButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addCenteredButton(panel, deleteTaskButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addCenteredButton(panel, listPriorityButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        addCenteredButton(panel, exitButton);

        addTaskButton.addActionListener(e -> addTask());
        modifyTaskButton.addActionListener(e -> modifyTask());
        deleteTaskButton.addActionListener(e -> deleteTask());
        listPriorityButton.addActionListener(e -> listPriority());
        exitButton.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private void addCenteredButton(JPanel panel, JButton button) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(button);
        buttonPanel.add(Box.createHorizontalGlue());

        panel.add(buttonPanel);
    }
    public void undoMethod(){
        controller.undo();
    }


    public void addTask() {
        boolean idExist;
        do {
            String id = JOptionPane.showInputDialog("ID de la tarea");
            idExist = controller.searchTask(id);
            if (idExist) {
                JOptionPane.showMessageDialog(null, "El ID ya existe, ingrese un ID único");
            } else {
                String title = JOptionPane.showInputDialog("Título: ");
                String description = JOptionPane.showInputDialog("Descripción: ");
                Date deadline = parseDate();
                String[] priorities = { "0. No prioritaria", "1. Poco prioritaria", "2. Prioritaria",
                        "3. Muy prioritaria" };
                int priority = JOptionPane.showOptionDialog(null, "¿Qué tan prioritaria es?", "Prioridad",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, priorities, priorities[0]);

                String msg = controller.addTask(id, title, description, deadline, priority);
                JOptionPane.showMessageDialog(null, msg);
            }
        } while (idExist);
    }

    public void deleteTask() {
        String idTask = JOptionPane.showInputDialog("Ingrese la clave de la tarea a eliminar");
        String msg = controller.removeTask(idTask);
        JOptionPane.showMessageDialog(null, msg);
    }

    public void modifyTask() {
        String taskId = JOptionPane.showInputDialog("Ingrese el ID de la tarea que desea modificar");

        if (controller.searchTask(taskId)) {
            String newTitle = "";
            String newDescription = "";
            Date newDeadline = null;
            int newPriority = 0;
            int modifyChoice;

            do {
                String modifyMenuMessage = "===== Menú de Modificación de Tarea =====\n"
                        + "1. Modificar Título\n"
                        + "2. Modificar Descripción\n"
                        + "3. Modificar Fecha Límite\n"
                        + "4. Modificar Prioridad\n"
                        + "0. Volver al menú principal";

                modifyChoice = Integer.parseInt(JOptionPane.showInputDialog(modifyMenuMessage));

                switch (modifyChoice) {
                    case 1:
                        newTitle = JOptionPane.showInputDialog("Nuevo Título: ");
                        break;
                    case 2:
                        newDescription = JOptionPane.showInputDialog("Nueva Descripción: ");
                        break;
                    case 3:
                        newDeadline = parseDate();
                        break;
                    case 4:
                        String[] priorities = { "No prioritaria", "Poco prioritaria", "Prioritaria",
                                "Muy prioritaria" };
                        newPriority = JOptionPane.showOptionDialog(null, "Nueva Prioridad", "Prioridad",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, priorities,
                                priorities[0]);
                        break;
                    case 0:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (modifyChoice != 0);

            String modifyMessage = controller.modifyTask(taskId, newTitle, newDescription, newDeadline, newPriority);
            JOptionPane.showMessageDialog(null, modifyMessage);
        } else {
            JOptionPane.showMessageDialog(null, "La tarea con el ID " + taskId + " no existe.");
        }
    }

    public Date parseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        while (date == null) {
            String dateString = JOptionPane.showInputDialog("Fecha Límite (yyyy-MM-dd):");

            try {
                date = dateFormat.parse(dateString);
                if (date.before(new Date())) {
                    JOptionPane.showMessageDialog(null, "Error: La fecha límite no puede ser una fecha pasada.");
                    date = null;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null,
                        "Formato de fecha incorrecto. Por favor, use el formato yyyy-MM-dd.");
            }
        }

        return date;
    }

    public void listPriority() {
        String list = controller.getListByPriority();
        JOptionPane.showMessageDialog(null, list);
    }
}