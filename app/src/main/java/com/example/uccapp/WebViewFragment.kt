package com.example.uccapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class WebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_webview, container, false)

        val webView: WebView = view.findViewById(R.id.webview)
        webView.webViewClient = WebViewClient()

        // Enable JavaScript
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val url = arguments?.getString("URL")
        if (url != null) {
            webView.loadUrl(url)
        }

        // Set the toolbar title
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Web View"

        return view
    }

    companion object {
        fun newInstance(url: String): WebViewFragment {
            val fragment = WebViewFragment()
            val args = Bundle()
            args.putString("URL", url)
            fragment.arguments = args
            return fragment
        }
    }
}
