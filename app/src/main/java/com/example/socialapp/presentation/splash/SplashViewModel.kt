package com.example.socialapp.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.socialapp.data.repo.AuthRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SplashViewModel(repo: AuthRepository) : ViewModel() {

    //currentUserFlow هو تيار بيانات (Flow) من الريبو بيرجع آخر مستخدم متسجل دخول.
    //
    //لو مفيش حد مسجل دخول، القيمة هتكون null.
    val isLoggedIn = repo.currentUserFlow
// . map 3aiza 27wl data al user l true or false lw 7d
        // msgl yb2a true lw m7d4 yb2a false
        .map { it != null }
        //ده بيخلي الـ Flow يتحول لـ StateFlow، يعني تيار بيانات له حالة ثابتة يمكن مراقبتها في الـ UI.
        //
        //viewModelScope → عشان الـ Flow يبقى مرتبط بـ ViewModel ويقف لما ViewModel يتدمر.
        //
        //SharingStarted.Eagerly → يبدأ التيار فورًا ويشارك البيانات لأي مشترك.
        //
        //false → القيمة الابتدائية (لو التيار ما بدأش يجيب بيانات بعد).


        .stateIn(viewModelScope, SharingStarted.Eagerly, false)
}