package shishirstudio.com.multilanguagetestapp
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    val SETTINGS: String = "settings"
    val LANG: String = "selected_language"
    val DEFAULT_LANGUAGE: String = "en"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocale()
        setContentView(R.layout.activity_main)
        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.title = resources.getString(R.string.app_name)
        }

        val bengaliBtn = findViewById<Button>(R.id.bengaliBtn)
        bengaliBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {

        val session: SharedPreferences = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
        val lang: String = session.getString(LANG, DEFAULT_LANGUAGE)

        if (lang == "en") {
            setLocale("bn")
            recreate()
        } else {
            setLocale("en")
            recreate()
        }
    }

    private fun setLocale(localeName: String) {
        val myLocale = Locale(localeName)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        resources.updateConfiguration(config, resources.displayMetrics)

        val session: SharedPreferences = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
        val sessionEditor: SharedPreferences.Editor = session.edit()
        sessionEditor.putString(LANG, localeName)
        sessionEditor.apply()
    }

    private fun loadLocale() {
        val session: SharedPreferences = getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
        val lang: String = session.getString(LANG, "")
        setLocale(lang)
    }

}
