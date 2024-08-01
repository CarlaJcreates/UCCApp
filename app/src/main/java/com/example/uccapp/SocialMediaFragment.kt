package com.example.uccapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class SocialMediaFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var socialMediaList: ArrayList<SocialMedia>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_social_media, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        socialMediaList = ArrayList()

        // Add social media items with corresponding logos
        socialMediaList.add(SocialMedia("Facebook", "https://www.facebook.com/uccjamaica", R.drawable.facebook_logo))
        socialMediaList.add(SocialMedia("Twitter", "https://twitter.com/uccjamaica", R.drawable.twitter_logo))
        socialMediaList.add(SocialMedia("Instagram", "https://www.instagram.com/uccjamaica", R.drawable.instagram_logo))

        recyclerView.adapter = SocialMediaAdapter(socialMediaList, ::openWebView)
        return view
    }

    private fun openWebView(url: String) {
        val fragment = WebViewFragment.newInstance(url)
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}

data class SocialMedia(val name: String, val url: String, val logo: Int)

class SocialMediaAdapter(
    private val socialMediaList: ArrayList<SocialMedia>,
    private val openWebView: (String) -> Unit
) : RecyclerView.Adapter<SocialMediaAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val socialMediaLogo: ImageView = view.findViewById(R.id.social_media_logo)
        val viewPageButton: MaterialButton = view.findViewById(R.id.view_page_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_social_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val socialMedia = socialMediaList[position]
        holder.socialMediaLogo.setImageResource(socialMedia.logo)
        holder.viewPageButton.setOnClickListener {
            openWebView(socialMedia.url)
        }
    }

    override fun getItemCount(): Int {
        return socialMediaList.size
    }
}
