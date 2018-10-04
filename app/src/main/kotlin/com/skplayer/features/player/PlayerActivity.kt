package com.skplayer.features.player

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.skplayer.core.platform.BaseActivity
class PlayerActivity : BaseActivity(), PlayerFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO() //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val INTENT_EXTRA_PARAM_VIDEO_URL= "INTENT_PARAM_MOVIE"

        fun callingIntent(context: Context, videoUrl: String): Intent {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra(INTENT_EXTRA_PARAM_VIDEO_URL, videoUrl)
            return intent
        }
    }
    override fun fragment() = PlayerFragment.newInstance(intent.getStringExtra(INTENT_EXTRA_PARAM_VIDEO_URL),"")

}
