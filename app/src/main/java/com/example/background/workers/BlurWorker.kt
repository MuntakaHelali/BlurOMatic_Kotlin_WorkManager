package com.example.background.workers

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R

class BlurWorker(ctx: Context, params: WorkerParameters): Worker(ctx,params) {

    override fun doWork(): Result {
        val appContext = applicationContext
        val resourceUri = inputData.getString(KEY_IMAGE_URI)
        makeStatusNotification("Blurring Image", appContext)

        return try {
//            val picture = BitmapFactory.decodeResource(
//                appContext.resources,
//                R.drawable.android_cupcake
//            )
            //blurBitmap(picture, appContext)
                if(TextUtils.isEmpty(resourceUri)){
                    Log.e(TAG, "Invalid URI")
                    throw IllegalArgumentException("Invalid input uri")
                }
            val picture = BitmapFactory.decodeStream(
                appContext.contentResolver.openInputStream(Uri.parse(resourceUri))
            )
            val outputUri = writeBitmapToFile(appContext, blurBitmap(picture, appContext))
            makeStatusNotification("Output is $outputUri", appContext)
            val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
            Result.success(outputData)
        } catch (throwable: Throwable){
            Log.e(TAG, "Error applying blur")
            Result.failure()
        }
    }
}