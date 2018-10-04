package com.skplayer.features.player

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.skplayer.core.navigation.Navigator
import com.skplayer.core.platform.BaseActivity
import com.skplayer.core.platform.BaseFragment
import com.skplayer.features.movies.MoviesActivity

/**
 * Created by sujeet on 6/9/18.
 */
class FolderActivity: BaseActivity(), FolderFragment.OnFragmentInteractionListener{
    override fun openPlayerFragment(videoPath: String) {
        TODO() //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun callingIntent(context: Context) = Intent(context, FolderActivity::class.java)
    }

    override fun fragment() = FolderFragment()

    override fun onFragmentInteraction(uri: Uri) {
        TODO() //To change body of created functions use File | Settings | File Templates.
    }
}