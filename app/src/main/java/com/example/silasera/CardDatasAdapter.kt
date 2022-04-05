package com.example.silasera

import android.media.Image
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CardDatasAdapter(

    private var cards: List<GuestCard>,
    private val onCardClick: (GuestCard) -> Unit

) : RecyclerView.Adapter<CardDatasAdapter.CardViewHolder>() {

    inner class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindData(card: GuestCard){
            with(itemView){
                val image = findViewById<ImageView>(R.id.card_image_view)
                val cardText = findViewById<TextView>(R.id.card_text_view)

                image.setImageResource(card.imageId)
                cardText.text = card.buttonText

                setOnClickListener{
                    onCardClick(card)
                }
            }
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder = CardViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.guest_card_view_design, parent, false)
    )


    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {

        val card = cards[position]
        holder.bindData(card)

    }

    override fun getItemCount(): Int = cards.size

}