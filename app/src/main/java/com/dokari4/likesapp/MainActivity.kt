package com.dokari4.likesapp

import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import com.dokari4.likesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val mBitmap = Bitmap.createBitmap(1000, 1000, Bitmap.Config.ARGB_8888)
    private val mCanvas = Canvas(mBitmap)
    private val mPaint = Paint()

    private val halOfWidth = (mBitmap.width/2).toFloat()
    private val halOfHeight = (mBitmap.height/2).toFloat()
    private val left = 150F
    private val top = 250F
    private val right = mBitmap.width - left
    private val bottom = mBitmap.height.toFloat() - 50F

    private val message = "Apakah kamu suka bermain?"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imageView.setImageBitmap(mBitmap)

        showFace()
        showEyes()
        showMouth(true)
        showText()

        binding.like.setOnClickListener {
            showFace()
            showEyes()
            showMouth(true)
        }
        binding.dislike.setOnClickListener {
            showFace()
            showEyes()
            showMouth(false)
        }

    }

    private fun showFace() {
        val face = RectF(left, top, right, bottom)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_left_skin, null)
        mCanvas.drawArc(face, 90F, 180F, false, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.yellow_right_skin, null)
        mCanvas.drawArc(face, 270F, 180F, false, mPaint)
    }
    private fun showEyes() {
        mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        mCanvas.drawCircle(halOfWidth - 100F, halOfHeight - 10F, 50F, mPaint)
        mCanvas.drawCircle(halOfWidth + 100F, halOfHeight - 10F, 50F, mPaint)

        mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
        mCanvas.drawCircle(halOfWidth - 120F, halOfHeight - 20F, 15F, mPaint)
        mCanvas.drawCircle(halOfWidth + 80F, halOfHeight - 20F, 15F, mPaint)
    }
    private fun showMouth(isHappy: Boolean) {
        when(isHappy) {
            true -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halOfWidth - 200F, halOfHeight - 100F, halOfWidth + 200F, halOfHeight + 400F)
                mCanvas.drawArc(lip, 25F, 130F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halOfWidth - 180F, halOfHeight, halOfWidth + 180F, halOfHeight + 380F)
                mCanvas.drawArc(mouth, 25F, 130F, false, mPaint)
            }
            false -> {
                mPaint.color = ResourcesCompat.getColor(resources, R.color.black, null)
                val lip = RectF(halOfWidth - 200F, halOfHeight + 250F, halOfWidth + 200F, halOfHeight + 350F)
                mCanvas.drawArc(lip, 0F, -180F, false, mPaint)

                mPaint.color = ResourcesCompat.getColor(resources, R.color.white, null)
                val mouth = RectF(halOfWidth - 180F, halOfHeight + 260F, halOfWidth + 180F, halOfHeight + 330F)
                mCanvas.drawArc(mouth, 0F, -180F, false, mPaint)
            }
        }
    }

    private fun showText() {
        val mPaintText = Paint(Paint.FAKE_BOLD_TEXT_FLAG).apply {
            textSize = 50F
            color = ResourcesCompat.getColor(resources, R.color.black, null)
        }
        val mBounds = Rect()
        mPaintText.getTextBounds(message, 0, message.length, mBounds)

        val x: Float = halOfWidth - mBounds.centerX()
        val y = 50F
        mCanvas.drawText(message, x, y, mPaintText)
    }
}