import java.util.*;

class Task {
    String name;
    String priority;
    boolean completed;

    Task(String name, String priority) {
        this.name = name;
        this.priority = priority;
        this.completed = false;
    }
}

public class SmartTodoManager {

    static ArrayList<Task> tasks = new ArrayList<>();

    // Priority ranking
    static int getPriorityValue(String priority) {
        switch (priority) {
            case "High": return 1;
            case "Medium": return 2;
            case "Low": return 3;
            default: return 4;
        }
    }

    // Add task
    static void addTask(String name, String priority) {
        tasks.add(new Task(name, priority));
        System.out.println("Task added successfully!");
    }

    // View tasks (sorted)
    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        tasks.sort(Comparator.comparingInt(t -> getPriorityValue(t.priority)));

        System.out.println("\nYour Tasks:");
        int i = 1;
        for (Task t : tasks) {
            String status = t.completed ? "✔" : "✘";
            System.out.println(i + ". " + t.name + " | Priority: " + t.priority + " | Done: " + status);
            i++;
        }
    }

    // Complete task
    static void completeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).completed = true;
            System.out.println("Task marked as completed!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    // Delete task
    static void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Task deleted!");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Smart To-Do Manager ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = sc.nextLine();
                    addTask(name, priority);
                    break;

                case 2:
                    viewTasks();
                    break;

                case 3:
                    viewTasks();
                    System.out.print("Enter task number to complete: ");
                    int cIndex = sc.nextInt() - 1;
                    completeTask(cIndex);
                    break;

                case 4:
                    viewTasks();
                    System.out.print("Enter task number to delete: ");
                    int dIndex = sc.nextInt() - 1;
                    deleteTask(dIndex);
                    break;

                case 5:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}