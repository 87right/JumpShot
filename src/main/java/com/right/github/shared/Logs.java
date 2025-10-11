package main.java.com.right.github.shared;

public class Logs {
    public static void Info(String pDescription){
        System.out.println("[   INFO   ]" + pDescription);
    }
    public static void Successful(String pDescription){
        System.out.println("[SUCCESS]" + pDescription);
    }
    public static void Warn(String pDescription){
        System.out.println("[   WARN  ]" + pDescription);
    }
}
