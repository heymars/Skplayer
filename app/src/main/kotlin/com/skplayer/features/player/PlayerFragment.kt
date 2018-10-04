package com.skplayer.features.player

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.ExoPlayer
import com.skplayer.R
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import kotlinx.android.synthetic.main.fragment_player.*
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.skplayer.core.platform.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : BaseFragment() {

    private lateinit var exoPlayer : ExoPlayer
    override fun layoutId() = R.layout.fragment_player

    // TODO: Rename and change types of parameters
    private var videoUrlPath: String? = null
    private var mParam2: String? = null

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        if (arguments != null) {
            videoUrlPath = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bandwidthMeter = DefaultBandwidthMeter()
        val trackSelector = DefaultTrackSelector(AdaptiveTrackSelection.Factory(bandwidthMeter))
        exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector)
//        exoPlayer.addListener(this)

        val videoUri = Uri.parse(videoUrlPath)
        val dataSourceFactory = DefaultDataSourceFactory(activity!!, "exoplayer_video")

        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(videoUri)
        exoPlayer.prepare(videoSource)
        exoPlayer.playWhenReady = true
        player_view.player = exoPlayer

    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

    override fun onPause() {
        exoPlayer.playWhenReady = false
        super.onPause()
    }

    override fun onDestroy() {
        exoPlayer.stop()
        exoPlayer.release()
        super.onDestroy()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PlayerFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(videoUrl: String, param2: String): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, videoUrl)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
