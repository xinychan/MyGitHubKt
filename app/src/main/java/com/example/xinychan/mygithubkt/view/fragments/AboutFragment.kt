package com.example.xinychan.mygithubkt.view.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xinychan.mygithubkt.R
import com.example.xinychan.mygithubkt.utils.markdownText
import com.example.xinychan.mygithubkt.view.common.CommonSinglePageFragment

/**
 * 关于界面
 */
class AboutFragment : CommonSinglePageFragment() {

    // 初始化 licenses.md 是耗时操作，创建全局变量避免每次都初始化 licenses.md
    private var mLicenseText: String? = null
    private var mLicenseDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = View.inflate(context, R.layout.fragment_about, null)
        val tvLicense: TextView = view.findViewById<TextView>(R.id.tv_license)
        mLicenseText = context?.assets?.open("licenses.md")?.bufferedReader()?.readText().toString()
        mLicenseDialog = licensesDialog()
        tvLicense.setOnClickListener { mLicenseDialog?.show() }
        return view
    }

    private fun licensesDialog(): AlertDialog {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        val view: View = View.inflate(context, R.layout.dialog_license, null)
        val tvContent: TextView = view.findViewById<TextView>(R.id.tv_license_content)
        tvContent.markdownText = mLicenseText ?: "load license error"
        dialog.setView(view)
        return dialog.create()
    }
}