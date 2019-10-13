package com.rozie.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_place.*

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Questions>()
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        rvSwipeQuiz.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvSwipeQuiz.adapter = questionAdapter

        for (i in Questions.SWIPE_QUIZ_QUESTIONS.indices) {
            questions.add(Questions(Questions.SWIPE_QUIZ_QUESTIONS[i], Questions.SWIPE_QUIZ_ANSWERS[i]))
            rvSwipeQuiz.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        }

        createItemTouchHelper().attachToRecyclerView(rvSwipeQuiz)
    }


    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            //        true direction --> swipe to right = 8
            //        false direction --> swipe to left = 4

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val questionPosition = questions[viewHolder.adapterPosition]

                if ((questionPosition.answer && direction == 8) || !questionPosition.answer && direction == 4) {
                    Snackbar.make(
                        rvSwipeQuiz,
                        "Correct! The answer was: " + questionPosition.answer,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                else {
                    Snackbar.make(
                        rvSwipeQuiz,
                        "Incorrect! The answer was: " + questionPosition.answer,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                questionAdapter.notifyItemChanged(viewHolder.adapterPosition)
            }

        }
        return ItemTouchHelper(callback)
    }
}