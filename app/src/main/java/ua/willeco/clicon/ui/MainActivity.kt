package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.ActivityMainBinding
import ua.willeco.clicon.mvp.contract.MainActivityContract


class MainActivity :AppCompatActivity(), MainActivityContract.View, NavigationView.OnNavigationItemSelectedListener {

    lateinit var binding:ActivityMainBinding
    private var doubleBackPressed:Boolean = false
    private val mBottomSheetBehavior: BottomSheetBehavior<View?>? = null
    private lateinit var fragmentTramsactor:FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initView()
    }

    override fun initView() {

        setSupportActionBar(toolbar)
        val drawerLayout:DrawerLayout = binding.drawerLayout//findViewById(R.id.drawer_layout)
        val navView: NavigationView = binding.navView//findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

        changeViewFragment(FacilitiesFragment.newInstance())
    }

    override fun changeViewFragment(fragment: Fragment) {
        fragmentTramsactor = supportFragmentManager.beginTransaction()
        fragmentTramsactor.replace(R.id.frame_container,fragment)

        fragmentTramsactor.addToBackStack(null)
        fragmentTramsactor.commit()
    }

    override fun getContext(): Context {
        return  this
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackPressed) {
                //SystemUtility.exitFromApp(this)
                super.onBackPressed()
                return
            }

            mBottomSheetBehavior?.let {
                if (it.state == BottomSheetBehavior.STATE_EXPANDED) {
                    it.state = BottomSheetBehavior.STATE_COLLAPSED
                }else{
                    return
                }
            }

            this.doubleBackPressed = true

            Toast.makeText(this, getString(R.string.double_back_pressed), Toast.LENGTH_SHORT).show()

            Handler().postDelayed({ doubleBackPressed = false }, 2000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_devices -> {

            }
            R.id.nav_widgets -> {
                //showHideSelectWidgetPanel()
            }
            R.id.nav_groups -> {

            }
            R.id.nav_statistic -> {

            }
            R.id.nav_message -> {

            }
            R.id.nav_call -> {

            }
            R.id.nav_help -> {

            }
            R.id.nav_settings -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}
