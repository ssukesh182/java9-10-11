import java.io.*;
import java.util.*;

public class FileOperations {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== File Operations Menu =====");
            System.out.println("1. Check if file exists at location");
            System.out.println("2. Get last modification date and time");
            System.out.println("3. Rename an existing file");
            System.out.println("4. Create a directory/folder");
            System.out.println("5. Check if file can be read");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            try {
                if (ch == 1) {
                    System.out.print("Enter file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    if (f.exists())
                        System.out.println("File found at: " + f.getAbsolutePath());
                    else
                        throw new FileNotFoundException("File not found at: " + path);
                }

                else if (ch == 2) {
                    System.out.print("Enter file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    if (!f.exists())
                        throw new FileNotFoundException("File not found!");
                    Date d = new Date(f.lastModified());
                    System.out.println("Last modified on: " + d);
                }

                else if (ch == 3) {
                    System.out.print("Enter current file path: ");
                    String oldPath = sc.nextLine();
                    File oldFile = new File(oldPath);
                    if (!oldFile.exists())
                        throw new FileNotFoundException("File not found!");

                    System.out.print("Enter new file name (with path or just name): ");
                    String newPath = sc.nextLine();
                    File newFile = new File(newPath);
                    if (oldFile.renameTo(newFile))
                        System.out.println("File renamed successfully.");
                    else
                        System.out.println("Rename failed.");
                }

                else if (ch == 4) {
                    System.out.print("Enter directory path to create: ");
                    String dirPath = sc.nextLine();
                    File dir = new File(dirPath);
                    if (dir.exists())
                        System.out.println("Directory already exists.");
                    else if (dir.mkdirs())
                        System.out.println("Directory created at: " + dir.getAbsolutePath());
                    else
                        System.out.println("Directory creation failed.");
                }

                else if (ch == 5) {
                    System.out.print("Enter file path: ");
                    String path = sc.nextLine();
                    File f = new File(path);
                    if (!f.exists())
                        throw new FileNotFoundException("File not found!");
                    if (f.canRead())
                        System.out.println("File is readable.");
                    else
                        System.out.println("File cannot be read.");
                }

                else if (ch == 6) {
                    System.out.println("Exiting program...");
                    break;
                }

                else {
                    System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        sc.close();
    }
}
