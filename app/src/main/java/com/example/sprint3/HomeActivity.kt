package com.example.sprint3

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sprint3.databinding.ActivityHomeBinding

/**
 * PRINCIPIOS QUE HE TENIDO EN CUENTA
 *
 * 1. Diseño (UI)
 *    - Orden claro: imagen, título, subtítulo y botón.
 *    - Pocos colores (negro, dorado y blanco) para que se parezca al cartel del evento.
 *    - Márgenes grandes para que la pantalla tenga esapcio suficiente.
 *    - Cada componente está personalizado:
 *        · Imagen: fondo con shape de degradado (efecto foco) y esquinas redondeadas.
 *        · Título: fuente más gruesa y color dorado.
 *        · Subtítulo: texto blanco, centrado y con más espacio entre líneas.
 *        · Botón: shape dorado con esquinas redondeadas.
 *
 * 2. Experiencia de usuario (UX)
 *    - Solo hay una acción principal que es el botón.
 *    - El botón responde al tocarlo y muestra un Toast avisando de que
 *      todavía no está implementado.
 *    - La pantalla tiene ScrollView, así que no se corta en horizontal ni en móviles pequeños.
 *
 * 3. Accesibilidad
 *    - Buen contraste: texto negro sobre el botón dorado.
 *    - La imagen tiene contentDescription para que TalkBack la lea.
 *    - El título se marca como encabezado para que TalkBack lo anuncie como tal.
 *    - Los textos están en sp, así que se adaptan al tamaño de letra que elija el usuario.
 *    - El botón mide 56dp de alto (el mínimo recomendado es 48dp), fácil de tocar.
 *
 * 4. Buenas prácticas
 *    - Nada escrito a mano en el código: textos en strings.xml, colores en colors.xml
 *      y medidas en dimens.xml.
 *    - Los diseños del botón y de la imagen son shapes en XML.
 *    - Uso ViewBinding en vez de findViewById.
 */
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
        )
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        ViewCompat.setAccessibilityHeading(binding.title, true)
        binding.ivVelada.clipToOutline = true
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnInfo.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.velada_toast_not_implemented),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
