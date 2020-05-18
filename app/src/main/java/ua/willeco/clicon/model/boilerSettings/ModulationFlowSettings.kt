package ua.willeco.clicon.model.boilerSettings

import java.io.Serializable

class ModulationFlowSettings: FSaPWMincluded(),Serializable {
    private val pwmWorkTime = 5 // onPWM
    private val pwmIdleTime = 400 // offPWM
}