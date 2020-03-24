package sid.angel.androidmultithreading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ExceptionHandling extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        sendError(null, null);
        try {
         boolean val =   sendError(null, null);
        } catch (Exception e) {
            System.out.println("exception handled" + e.getMessage());
        }
    }


    public static boolean sendError(String one, String two) {
      //  sendError2(null, null);
//        System.out.println("will not reach here");
   //     boolean val = one.equals(two);

        String name = "";
        name.trim();
        System.out.println("hello" + name.substring(0,1));
        return false;

//        System.out.println(one.equals(two));
    }

    public static void sendError2(String one, String two) {
        System.out.println("will not reach here");
        boolean val = one.equals(two);
    }
}
