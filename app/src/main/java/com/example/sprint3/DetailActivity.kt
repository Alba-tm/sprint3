package com.example.sprint3

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint3.databinding.ActivityDetailBinding

/**
 * DetailActivity: pantalla que usa el componente propio MyCard.
 *
 * QUÉ HACE
 *  - Configura MyCard (título, subtítulo, descripción, imagen y botones) desde Kotlin.
 *
 * PRINCIPIOS QUE HE TENIDO EN CUENTA
 *  - ViewBinding para acceder a la card (binding.myCard), sin findViewById.
 *  - Accesibilidad: la imagen se añade con descripción para TalkBack, y las barras del
 *    sistema se ven bien sobre el fondo negro.
 *  - Textos en strings.xml: ni los textos de la card ni los de los Toast están escritos a mano.
 *  - Toda la lógica de la card está dentro de MyCard; esta pantalla solo la configura.
 *  - Los botones muestran un Toast para que el usuario siempre reciba respuesta.
 */
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupCard()
    }

    private fun setupCard() {
        binding.myCard.title = getString(R.string.card_title)
        binding.myCard.subtitle = getString(R.string.card_subtitle)
        binding.myCard.description = getString(R.string.card_description)
        binding.myCard.setImage(R.drawable.velada_vii, getString(R.string.velada_image_desc))

        binding.myCard.setPrimaryAction(getString(R.string.card_action_primary)) {
            showNotImplemented()
        }
        binding.myCard.setSecondaryAction(getString(R.string.card_action_secondary)) {
            showNotImplemented()
        }
    }

    private fun showNotImplemented() {
        Toast.makeText(
            this,
            getString(R.string.velada_toast_not_implemented),
            Toast.LENGTH_SHORT,
        ).show()
    }
}