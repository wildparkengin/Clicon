package ua.willeco.clicon

import android.os.Bundle
import android.os.Handler
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ua.willeco.clicon.adapters.AddWidgetAdapter
import ua.willeco.clicon.adapters.DevicesUserAdapter
import ua.willeco.clicon.adapters.UserWidgetAdapter
import ua.willeco.clicon.entities.TestModel
import kotlinx.android.synthetic.main.app_bar_main.*
import ua.willeco.clicon.animation.HideShowAnimation
import ua.willeco.clicon.entities.widgets.EnableWidget
import ua.willeco.clicon.enums.EventType
import ua.willeco.clicon.event_bus.RxBus
import ua.willeco.clicon.event_bus.RxEvent
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var doubleBackPressed:Boolean = false
    private var mBottomSheetBehavior: BottomSheetBehavior<View?>? = null
    private lateinit var changeTitleBarDisposable: Disposable

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

        initRecyclerByType(1)
        initLeftWidgetPanel()
        initRxBusEvent()
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            if (doubleBackPressed) {
                super.onBackPressed()
                return
            }

            mBottomSheetBehavior?.let {
                if (it.state == BottomSheetBehavior.STATE_EXPANDED) {
                    it.state = BottomSheetBehavior.STATE_COLLAPSED
                }else{
                    this.doubleBackPressed = true

                    Toast.makeText(this, getString(R.string.double_back_pressed), Toast.LENGTH_SHORT).show()

                    Handler().postDelayed({ doubleBackPressed = false }, 2000)
                }
            }
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
                initRecyclerByType(0)
            }
            R.id.nav_widgets -> {
                initRecyclerByType(1)
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

    fun initRxBusEvent(){
        changeTitleBarDisposable = RxBus.listen(RxEvent.EventChanges::class.java).subscribe{
            if (it.changesEvent== EventType.CLOSELEFTPANEL){
                showHideSelectWidgetPanel()
            }
        }
    }

    private fun initRecyclerByType(type:Int){
        val mainRecycler:RecyclerView = findViewById(R.id.rcv_main)
        val txtCardAddItemType:TextView = findViewById(R.id.txt_type_recycler_item_add)

        mainRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val dataWidget = ArrayList<TestModel>()
        dataWidget.add(TestModel("Phone", 1))
        dataWidget.add(TestModel("Watch", 2))
        dataWidget.add(TestModel("Note", 3))
        dataWidget.add(TestModel("Pin", 4))
        var typeAdapter: RecyclerView.Adapter<*>? = null

        when(type){
            0->{
                typeAdapter = DevicesUserAdapter(dataWidget)
                txtCardAddItemType.text = "Add device"
            }
            1->{
                typeAdapter = UserWidgetAdapter(dataWidget)
                txtCardAddItemType.text = "Add widget"
            }
        }
        //val widgetAdapter = UserWidgetAdapter(dataWidget)
        //mainRecycler.adapter = widgetAdapter
        if (typeAdapter!=null){
            mainRecycler.adapter = typeAdapter
            (mainRecycler.adapter as RecyclerView.Adapter<*>).notifyDataSetChanged()
        }
    }

    private fun configureAddItemType(itemType:Int) {
        // Get the fragment reference
        val addNewItemType = findViewById<CardView>(R.id.crd_empty)

        addNewItemType.setOnClickListener {
            when(itemType){
                0->{}
                1->{}
            }
        }
    }

    private fun initLeftWidgetPanel(){
        val imgClosePanel = findViewById<ImageView>(R.id.img_close_left_panel)

        imgClosePanel.setOnClickListener {
            showHideSelectWidgetPanel()
        }

        val listWidget = ArrayList<EnableWidget>()

        listWidget.add(EnableWidget("Котел","boiler"))
        listWidget.add(EnableWidget("КлиКон","clicon"))
        listWidget.add(EnableWidget("Статистика","statistic"))
        listWidget.add(EnableWidget("Сообщения","clicon"))
        listWidget.add(EnableWidget("Погода","statistic"))

        val rcv:RecyclerView = findViewById(R.id.rcv_add_widget)

        rcv.layoutManager = LinearLayoutManager(this)

        rcv.adapter = AddWidgetAdapter(this,listWidget)
    }

    private fun showHideSelectWidgetPanel(){
        val frameSelectWidget = findViewById<FrameLayout>(R.id.frame_widget_select)

        HideShowAnimation.setAnimation(frameSelectWidget)
    }
}
