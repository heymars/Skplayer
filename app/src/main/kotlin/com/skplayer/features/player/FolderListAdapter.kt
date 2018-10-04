package com.skplayer.features.player

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.skplayer.R
import com.skplayer.core.extension.inflate
import kotlinx.android.synthetic.main.layout_video_item.view.*
import android.widget.ImageView
import com.bumptech.glide.Glide
import android.media.ThumbnailUtils
import android.graphics.Bitmap
import android.provider.MediaStore
import java.io.ByteArrayOutputStream


/**
 * Created by sujeet on 6/9/18.
 */
class FolderListAdapter constructor(private val context: Context,private val videoList:MutableList<VideoFileModel>,val listener:ClickListener ): RecyclerView.Adapter<FolderListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(parent.inflate(R.layout.layout_video_item))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            val videoFileModel = videoList[position]
            viewHolder.fileName.text = videoFileModel.videoName
            viewHolder.fileSize.text = videoFileModel.videoSize
            viewHolder.itemView.setOnClickListener {
                listener.onItemClick(videoFileModel.videoUrl)
            }
//            val mediaMetadataRetriever = MediaMetadataRetriever()
//            mediaMetadataRetriever.setDataSource(videoFileModel.videoUrl, HashMap())
//            val bmFrame = mediaMetadataRetriever.getFrameAtTime(200)
        val bmThumbnail = ThumbnailUtils.createVideoThumbnail(videoFileModel.videoUrl, MediaStore.Video.Thumbnails.MICRO_KIND);
        val stream = ByteArrayOutputStream()
        bmThumbnail.compress(Bitmap.CompressFormat.PNG, 100, stream)
            Glide.with(context)
                    .asBitmap()
                    .load(stream.toByteArray())
                    .into(viewHolder.ivThumb)
    }

    override fun getItemCount() = videoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val fileName = itemView.file_name as TextView
            val fileSize = itemView.file_size as TextView
            val ivThumb = itemView.iv_thumbnail as ImageView
        }
        interface ClickListener {
            fun onItemClick(videoPath:String)
        }
}


