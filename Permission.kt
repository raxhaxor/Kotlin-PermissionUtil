package util.extension

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import student.bac.edu.my.app.App

/**
 * Created by Akash Saggu(R4X) on 12/9/18 at 18:53.
 * akashsaggu@protonmail.com
 * @Version 2
 * @Updated on 12/1/19
 */


fun Array<String>.withPermissions(
    activity: Activity,
    timeInMs: Long,
    allowBody: (() -> Unit)? = null,
    discardBody: (() -> Unit)? = null
) {
    if (checkPermissions(this)) {
        allowBody?.invoke()
    } else {
        val maxCount = timeInMs / 500
        var count = 0
        ActivityCompat.requestPermissions(
            activity,
            this,
            700
        )

        val handler = Handler()
        var runnable: Runnable? = null
        runnable = Runnable {
            if (checkPermissions(this)) {
                allowBody?.invoke()
                handler.removeCallbacksAndMessages(null)
                runnable = null
            }
            if (count > maxCount) {
                val mainHandler = Handler(Looper.getMainLooper())
                mainHandler.post { discardBody?.invoke() }
                handler.removeCallbacksAndMessages(null)
                runnable = null
            }
            count += 1
            handler.postDelayed(runnable, 500)
        }
        handler.postDelayed(runnable, 500)
    }
}

fun <T> T.checkPermissions(permissions: Array<String>): Boolean {
    var allPerm = true
    for (permission: String in permissions) {
        if (ContextCompat.checkSelfPermission(App.getApp(), permission)
            != PackageManager.PERMISSION_GRANTED
        ) {
            allPerm = false
        }
    }
    return allPerm
}
