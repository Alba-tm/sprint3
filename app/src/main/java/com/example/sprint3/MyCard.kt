package com.example.sprint3

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.sprint3.databinding.ViewMyCardBinding
import com.google.android.material.card.MaterialCardView

/**
 * MyCard: componente propio (una Card) que se puede reutilizar en cualquier pantalla.
 * PRINCIPIOS QUE HE TENIDO EN CUENTA
 *
 * 1. Flexible y reutilizable
 *    - Título, subtítulo, descripción e imagen son opcionales: si no se pone un texto,
 *      esa parte de la card se oculta y no deja huecos vacíos.
 *    - Los dos botones de acción también son opcionales y se pueden configurar desde fuera.
 *
 * 2. Imagen añadida de forma programática
 *    - Se hace con setImage(), que usa ImageView.setImageResource / setImageDrawable.
 *
 * 3. Accesibilidad
 *    - La imagen lleva una descripción para TalkBack. Si no se da descripción, se marca
 *      como decorativa y TalkBack la ignora.
 *    - El título se marca como encabezado.
 *    - Buen contraste de colores (texto claro sobre fondo oscuro) y textos en sp.
 *    - Los botones miden mínimo 48dp de alto.
 *
 * 4. Buenas prácticas
 *    - Uso ViewBinding (ViewMyCardBinding) en vez de findViewById.
 *    - Nada escrito a mano: colores, medidas y textos vienen de colors, dimens y strings.
 *    - La card hereda de MaterialCardView, así no hay que dibujar bordes ni sombras a mano.
 *    - El diseño interno está en su propio XML (view_my_card.xml), separado del código.
 */
class MyCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.materialCardViewStyle,
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding = ViewMyCardBinding.inflate(LayoutInflater.from(context), this, true)

    var title: CharSequence?
        get() = binding.tvCardTitle.text
        set(value) = showText(binding.tvCardTitle, value)

    var subtitle: CharSequence?
        get() = binding.tvCardSubtitle.text
        set(value) = showText(binding.tvCardSubtitle, value)

    var description: CharSequence?
        get() = binding.tvCardDescription.text
        set(value) = showText(binding.tvCardDescription, value)

    init {
        radius = resources.getDimension(R.dimen.card_corner_radius)
        cardElevation = resources.getDimension(R.dimen.card_elevation)
        strokeWidth = resources.getDimensionPixelSize(R.dimen.card_stroke_width)
        strokeColor = ContextCompat.getColor(context, R.color.velada_dorado)
        setCardBackgroundColor(ContextCompat.getColor(context, R.color.velada_card))

        ViewCompat.setAccessibilityHeading(binding.tvCardTitle, true)
    }

    fun setImage(@DrawableRes imageRes: Int, contentDescription: String? = null) {
        binding.ivCardImage.setImageResource(imageRes)
        showImage(contentDescription)
    }

    fun setPrimaryAction(text: CharSequence?, onClick: View.OnClickListener?) {
        showText(binding.btnCardPrimary, text)
        binding.btnCardPrimary.setOnClickListener(onClick)
        updateActionsVisibility()
    }

    fun setSecondaryAction(text: CharSequence?, onClick: View.OnClickListener?) {
        showText(binding.btnCardSecondary, text)
        binding.btnCardSecondary.setOnClickListener(onClick)
        updateActionsVisibility()
    }

    private fun showImage(contentDescription: String?) {
        binding.ivCardImage.visibility = View.VISIBLE
        if (contentDescription.isNullOrBlank()) {
            binding.ivCardImage.contentDescription = null
            binding.ivCardImage.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        } else {
            binding.ivCardImage.contentDescription = contentDescription
            binding.ivCardImage.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        }
    }

    private fun showText(view: TextView, text: CharSequence?) {
        view.text = text
        view.visibility = if (text.isNullOrBlank()) View.GONE else View.VISIBLE
    }

    private fun updateActionsVisibility() {
        val hayBotones = (binding.btnCardPrimary.visibility == VISIBLE) ||
                (binding.btnCardSecondary.visibility == VISIBLE)
        binding.llCardActions.visibility = if (hayBotones) View.VISIBLE else View.GONE
    }
}