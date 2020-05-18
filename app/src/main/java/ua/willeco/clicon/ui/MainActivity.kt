package ua.willeco.clicon.ui

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.app_bar_main.*
import ua.willeco.clicon.R
import ua.willeco.clicon.adapters.AddWidgetAdapter
import ua.willeco.clicon.model.widgets.EnableWidget
import ua.willeco.clicon.utility.ViewsElementsUtill


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var doubleBackPressed:Boolean = false
    private val mBottomSheetBehavior: BottomSheetBehavior<View?>? = null
    private val isTransactionSafe:Boolean = false
    private val isTransactionPending:Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val drawerLayout:DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        initLeftWidgetPanel()
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

    private fun initLeftWidgetPanel(){
        val imgClosePanel = findViewById<ImageView>(R.id.img_close_left_panel)
        val rcv:RecyclerView = findViewById(R.id.rcv_add_widget)

        imgClosePanel.setOnClickListener {
            showHideSelectWidgetPanel()
        }

        val listWidget = ArrayList<EnableWidget>()

        listWidget.add(EnableWidget("Котел","boiler"))
        listWidget.add(EnableWidget("КлиКон","clicon"))
        listWidget.add(EnableWidget("Статистика","statistic"))
        listWidget.add(EnableWidget("Сообщения","clicon"))
        listWidget.add(EnableWidget("Погода","statistic"))

        rcv.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rcv.adapter = AddWidgetAdapter(this,listWidget)
    }

    private fun showHideSelectWidgetPanel(){
        val frameSelectWidget = findViewById<FrameLayout>(R.id.frame_widget_select)
        ViewsElementsUtill.setAnimationToView(frameSelectWidget)
    }

    override fun onStop() {
        //SystemUtility.exitFromApp(this)
        super.onStop()
    }

}
