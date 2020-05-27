package ua.willeco.clicon.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import ua.willeco.clicon.R
import ua.willeco.clicon.mvp.BaseActivity
import ua.willeco.clicon.mvp.contract.SplashActivityContract
import ua.willeco.clicon.mvp.presenter.SplashActivityPresenter

class SplashActivity :BaseActivity<SplashActivityPresenter>(), SplashActivityContract.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        presenter.onViewCreated()
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun toTheNextActivity(isSignIn: Boolean) {
        try {
            val intent = when(isSignIn){
                true ->{
                    Intent(this, MainActivity::class.java)
                }
                false ->{
                    Intent(this, AuthorizationActivity::class.java)
                }
            }
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        presenter.onViewDestroyed()
        super.onDestroy()
    }

    override fun instantiatePresenter(): SplashActivityPresenter {
        return SplashActivityPresenter(this)
    }

}
