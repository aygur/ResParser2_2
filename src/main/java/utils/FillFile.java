package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dmitrii on 12.02.17.
 */
public class FillFile extends Thread {

    private String fileName;

    public FillFile( String fileName) {
        this.fileName = fileName;
        this.start();
    }

    @Override
    public void run() {
        try(FileOutputStream out=new FileOutputStream(fileName);
            PrintWriter pw = new PrintWriter(out))
        {
            for (int i = -99; i <=100; i++){
                if(i%10 == 0){
                    pw.println(i+" ");

                }
                //pw.print(i+"  ");

            }
            pw.flush();
            System.out.println("Сгенерирован файл "+ fileName);

        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
