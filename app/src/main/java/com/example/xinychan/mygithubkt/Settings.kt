package com.example.xinychan.mygithubkt

import com.example.xinychan.common.Preference

object Settings {
    var email: String by Preference(AppContext, "email", "")
    var password: String by Preference(AppContext, "password", "")
}