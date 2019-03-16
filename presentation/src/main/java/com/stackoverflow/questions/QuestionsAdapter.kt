package com.stackoverflow.questions

import android.graphics.Typeface
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.stackoverflow.R
import com.stackoverflow.entities.Question
import kotlinx.android.synthetic.main.item_view.view.*
import java.text.SimpleDateFormat
import java.util.*


class QuestionsAdapter : RecyclerView.Adapter<QuestionsAdapter.ViewHolder>() {

    private val questions: MutableList<Question> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false))
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    fun addQuestions(questions: List<Question>) {
        this.questions.addAll(questions)
        notifyDataSetChanged()
    }

    fun clear() {
        this.questions.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question): Unit = with(itemView) {
            custom_view_group.removeAllViews()
            for (i in question.tags) {
                val view: TextView = LayoutInflater.from(itemView.context).inflate(R.layout.item_view_tags, custom_view_group, false) as TextView
                view.text = i
                custom_view_group.addView(view)
            }
            rating.text = question.score.toString()
            title.text = question.title
            username.text = question.owner.display_name
            score.text = question.owner.reputation.toString()
            view.text = "${question.view_count} views"
            answers.text = "${question.answer_count} answers"
            date.text = getTime(question.creation_date)

            if (!question.is_answered) {
                answers.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_color))
                answers.typeface = Typeface.DEFAULT
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    answers.backgroundTintList = ContextCompat.getColorStateList(itemView.context, R.color.white_back)
                }
            } else {
                answers.setTextColor(ContextCompat.getColor(itemView.context, R.color.green))
                answers.typeface = Typeface.DEFAULT_BOLD
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    answers.backgroundTintList = ContextCompat.getColorStateList(itemView.context, R.color.green_light)
                }
            }
        }

        private fun getTime(time: Long) : String {
            val date = Date(time)
            val dateFormat = SimpleDateFormat("MMM d", Locale.ROOT)
            val timeFormat = SimpleDateFormat("HH:mm", Locale.ROOT)
            val one = dateFormat.format(date)
            val two = timeFormat.format(date)
            return "answered $one at $two"
        }
    }
}