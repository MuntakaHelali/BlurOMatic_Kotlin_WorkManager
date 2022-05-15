package com.example.background.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.background.OUTPUT_PATH
import java.io.File
import java.lang.Exception


/**
 * Cleans up temporary files generated during blurring process
 */
private const val TAGa ="CleanupWorker"
class CleanupWorker(ctx: Context, params: WorkerParameters):Worker(ctx, params) {
    override fun doWork(): Result {
        // Makes a notification when the work starts and slows down the work so that
        // it's easier to see each WorkRequest start, even on emulated devices
        makeStatusNotification("Cleaning up old temporary files", applicationContext)
        sleep()

        return try {
//            storing the file directory path of the blur image created
            val outputDirectory = File(applicationContext.filesDir, OUTPUT_PATH)
//            If that path exits then get all the files
            if(outputDirectory.exists()){
                val entries = outputDirectory.listFiles()
                if(entries != null) {
//                    for every file in the output directory,
                    //check to see if it has a name and ends with .png
                    for (entry in entries){
                        val name = entry.name
                        if(name.isNotEmpty() && name.endsWith(".png")) {
//                      Deletes the files created by the program since the blur image will be a .png
                            val deleted = entry.delete()
                            Log.i(TAGa, "Deleted $name - $deleted")
                        }
                    }
                }
            }
            Result.success()
        } catch (exception: Exception){
            exception.printStackTrace()
            Result.failure()
        }
    }
}