package com.github.willcode4fun.tools.term;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;



/**
 * Created by DECOSTT on 17/07/2017.
 */
public abstract class Term<T> {

    public static final String STOP_CHAR = "x";
    private T model;

    private int x,y;

    private Map<String, TermCallback<T>> callbacks = new HashMap<>();

    public Term(T model){
        this.model = model;
    }

    public abstract void paint( T model );

    public void start(){
        try {
            this.paint(model);
            setConcoleCharMode();
            Scanner scanner= new Scanner(System.in);
            scanner.useDelimiter("");
            boolean stop = false;
            while(!stop ){
                if(scanner.hasNext()) {
                    String c = scanner.next();
                    if (STOP_CHAR.equals(c)) {
                        stop = true;
                    } else {
                        TermCallback<T> callback = callbacks.get(c);
                        if (callback != null) {
                            callback.apply(model);
                            cleanConsole();
                            this.paint(model);
                        }
                    }
                } else {
                    Thread.sleep(50);
                }
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
           //setConcoleLineMode();
        }
    }

    public Term<T> addCallback(String key, TermCallback<T> callback){
        callbacks.put(key,callback);
        return this;
    }

    static class MyModel{
        int i = 0;
    }

    public static void main(String args[]) throws IOException, InterruptedException {
       // Runtime.getRuntime().exec(new String[]{"cmd","dir"}).waitFor();
        //System.out.println("TOTO :"+ Kernel32.INSTANCE.GetStdHandle(11));
        setConcoleCharMode();
  /*      new Term<MyModel>(new MyModel()) {
            @Override
            public void paint(MyModel model) {
                System.out.print(model.i);
            }
        }
        .addCallback("a", (model)->  model.i++)
        .addCallback("z", (model)->  model.i--)
        .start();
        */

        printCount();




        Scanner scanner= new Scanner(System.in);
        scanner.useDelimiter("");
        //For string

        boolean stop = false;
        while(!stop ){
            String c = scanner.next();
            if(STOP_CHAR.equals(c)){
                stop = true;
            } else {

            }

        }
        setConcoleLineMode();


        System.out.println("Done !");
    }

    private static final void setConcoleCharMode() throws IOException, InterruptedException {
        WinNT.HANDLE stdHandle = Kernel32.INSTANCE.GetStdHandle(-10);
        IntByReference ptrMode = new IntByReference();
        Kernel32.INSTANCE.GetConsoleMode(stdHandle, ptrMode);
        Kernel32.INSTANCE.SetConsoleMode(stdHandle,ptrMode.getValue() & ~(2));
    }
    private static final void setConcoleLineMode() {
        WinNT.HANDLE stdHandle = Kernel32.INSTANCE.GetStdHandle(-10);
        IntByReference ptrMode = new IntByReference();
        Kernel32.INSTANCE.GetConsoleMode(stdHandle, ptrMode);
        Kernel32.INSTANCE.SetConsoleMode(stdHandle,ptrMode.getValue() | ~(2));
    }
    private static void printCount() throws IOException, InterruptedException {
        for (int i = 3; i < 10; i++) {
            cleanConsole();
            printSquare(5,i);
            Thread.sleep(50);
        }
    }
    private static void cleanConsole() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        //OutputStream outputStream = Runtime.getRuntime().exec(new String[]{"cmd", "/c", "cls"}).getOutputStream();

        //ByteArrayOutputStream os = new ByteArrayOutputStream();
        //aClass.outputStreamMethod(os);
        //String aString = new String(os.toByteArray(),"UTF-8");
        //System.out.println("Clear?"+aString);
        //System.out.print("\033[H\033[2J");
        //System.out.flush();
        //System.out.println("Clear");
    }
    public static final String ANSI_ROOT = "\u001B[";
    public static final String ANSI_FG = ANSI_ROOT+"3";
    public static final String ANSI_BG = ANSI_ROOT+"4";
    public static final String ANSI_BOLD = ANSI_ROOT+"1m";
    public static final String ANSI_ULINE = ANSI_ROOT+"4m";

    public static final String ANSI_RESET = ANSI_ROOT+"0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = (char)27+"[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static void printSquare(int h, int l){
        StringBuilder sb = new StringBuilder();
        sb.append(ANSI_RED);
        sb.append("┌");
        for (int i = 0; i < l; i++) {
            sb.append("─");
        }
        sb.append("┐");
        sb.append(ANSI_RESET);
        System.out.println(sb.toString());
        for (int j = 0; j < h; j++) {
            sb = new StringBuilder();
            //sb.append(ANSI_RED);
            sb.append("│");
            //sb.append(ANSI_RESET);
            for (int i = 0; i < l; i++) {
                sb.append("Z");
            }
            //sb.append(ANSI_RED);
            sb.append("│");
            //sb.append(ANSI_RESET);
            System.out.println(sb.toString());
        }



        sb = new StringBuilder();
        //sb.append(ANSI_RED);
        sb.append("└");
        for (int i = 0; i < l; i++) {
            sb.append("─");
        }
        sb.append("┘");
        //sb.append(ANSI_RESET);
        System.out.println(sb.toString());

    }
}
