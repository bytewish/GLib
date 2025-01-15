package com.gx.glib.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri

/**
 * This is a utility class for launching third-party apps.
 */
object AppOpener {

    /**
     * To directly open a user's TikTok profile
     * tiktokLink: https://www.tiktok.com/@user
     */
    fun openTikTokUserProfile(ac: Activity, tiktokLink:String){
        val tikTokUri = Uri.parse(tiktokLink)
        val intent = Intent(Intent.ACTION_VIEW,tikTokUri)
        intent.resolveActivity(ac.packageManager)?.let {
            ac.startActivity(intent)
        }
    }

}