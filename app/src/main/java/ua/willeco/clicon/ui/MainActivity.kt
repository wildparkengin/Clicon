package ua.willeco.clicon.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ua.willeco.clicon.R
import ua.willeco.clicon.databinding.ActivityMainBinding
import ua.willeco.clicon.factory.SelectedFragmentFactory
import ua.willeco.clicon.mvp.view.MainActivityView
import ua.willeco.clicon.utility.Constants


class MainActivity :AppCompatActivity(), MainActivityView{

    private lateinit var binding:ActivityMainBinding
    private lateinit var view: View
    private var doubleBackPressed:Boolean = false
    private val customFragmentFactory = SelectedFragmentFactory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = customFragmentFactory
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
        initView()
    }

    override fun initView() {
        setContentView(view)
        setSupportActionBar(binding.include.toolbarCustomLayout)
        initNavigationView()


//        val drawerLayout:DrawerLayout = binding.drawerLayout//findViewById(R.id.drawer_layout)
//        val navView: NavigationView = binding.navView//findViewById(R.id.nav_view)
//        val toggle = ActionBarDrawerToggle(
//            this, drawerLayout, bar,
//            R.string.navigation_drawer_open,
//            R.string.navigation_drawer_close
//        )
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        navView.setNavigationItemSelectedListener(this)
//
//        changeViewFragment(FacilitiesFragment.newInstance().)
    }

    override fun initNavigationView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val tempNameFragment = when(it.itemId){
                R.id.app_menu_home ->{
                    HomeFragment::class.java.name
                }
                else -> ""
            }
            if (tempNameFragment.isNotEmpty()){
                changeViewFragment(tempNameFragment)
            }
            true
        }

        binding.bottomNavigation.selectedItemId = R.id.app_menu_home
    }

    override fun changeViewFragment(fragmentName: String) {
        val fragment = customFragmentFactory.instantiate(classLoader,fragmentName)
        try {
            val f = supportFragmentManager.findFragmentByTag(fragmentName)?.isAdded
            if (f==null || f==false){
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_container,fragment,fragmentName)
                    .commitNow()
            }
        }catch (e:Exception){
            print(e.localizedMessage)
        }
    }

    override fun getViewContext(): Context {
        return this
    }

    override fun onBackPressed() {
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            if (doubleBackPressed) {
//                //SystemUtility.exitFromApp(this)
//                super.onBackPressed()
//                return
//            }
//
////            mBottomSheetBehavior?.let {
////                if (it.state == BottomSheetBehavior.STATE_EXPANDED) {
////                    it.state = BottomSheetBehavior.STATE_COLLAPSED
////                }else{
////                    return
////                }
////            }
//
//            this.doubleBackPressed = true
//
//            Toast.makeText(this, getString(R.string.double_back_pressed), Toast.LENGTH_SHORT).show()
//
//            Handler().postDelayed({ doubleBackPressed = false }, 2000)
//        }
    }

}
