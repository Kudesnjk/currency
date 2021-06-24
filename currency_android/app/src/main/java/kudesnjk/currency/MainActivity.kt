package kudesnjk.currency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kudesnjk.currency.presentation.CurrencyFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, CurrencyFragment.newInstance())
                    .commitNow()
        }
    }
}