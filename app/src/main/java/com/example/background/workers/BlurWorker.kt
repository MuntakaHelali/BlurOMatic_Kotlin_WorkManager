package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.R

class BlurWorker(ctx: Context, params: WorkerParameters): Worker(ctx,params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        makeStatusNotification("Blurring Image", appContext)

        return try {
            val picture = BitmapFactory.decodeResource(
                appContext.resources,
                R.drawable.android_cupcake
            )
            //blurBitmap(picture, appContext)
            val outputUri = writeBitmapToFile(appContext, blurBitmap(picture, appContext))
            makeStatusNotification("Output is $outputUri", appContext)
            Result.success()
        } catch (throwable: Throwable){
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}