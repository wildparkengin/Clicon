package ua.willeco.clicon.entities.boilerSettings

import ua.willeco.clicon.entities.boilerSettings.BoilerSettings

open class FSaPWMincluded: BoilerSettings() {
    private val fuelScrewWorkTime = 10
    private val fuelScrewIdleTime = 400
    private val pwm = 5
}