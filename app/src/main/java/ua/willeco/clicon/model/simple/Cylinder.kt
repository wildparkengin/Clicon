package ua.willeco.clicon.model.simple

import java.io.Serializable

class Cylinder : Serializable{
    private val serialVersionUID = 1899102561634821375L
    /**
     * Must be >= 0.
     */
    private val foldedTime = 10
    /**
     * Must be >= 0.
     */
    private val unfoldedTime = 10
    /**
     * Must be one of "fold" or "unfold".
     */
//    private val state = MessageBuilder.FOLD
//
//    fun getDisabledJson(): String {
//        return JsonBuilder()
//            .addUnit(MessageBuilder.FOLDED_TIME, 0)
//            .addUnit(MessageBuilder.UNFOLDED_TIME, 0)
//            .build()
//    }
//
//    fun getFullJson(): String {
//        return JsonBuilder()
//            .addUnit(MessageBuilder.FOLDED_TIME, this.foldedTime)
//            .addUnit(MessageBuilder.UNFOLDED_TIME, this.unfoldedTime)
//            .addUnit(MessageBuilder.STATE, this.state)
//            .build()
//    }
//
//    fun fromFullJson(json: String): Cylinder {
//        val jsonObject = AppUtils.getJsonObjectFromString(json)
//        setFoldedTime(jsonObject.get(MessageBuilder.FOLDED_TIME).getAsInt())
//        setUnfoldedTime(jsonObject.get(MessageBuilder.UNFOLDED_TIME).getAsInt())
//        setState(jsonObject.get(MessageBuilder.STATE).getAsString())
//        return this
//    }
//
//    fun update(jsonObject: JsonObject) {
//        setFoldedTime(jsonObject.get(MessageBuilder.FOLDED_TIME).getAsInt())
//        setUnfoldedTime(jsonObject.get(MessageBuilder.UNFOLDED_TIME).getAsInt())
//    }
//
//    fun getFoldedTime(): Int {
//        return foldedTime
//    }
//
//    /**
//     * Must be >= 0.
//     */
//    fun setFoldedTime(foldedTime: Int) {
//        AppUtils.validateTime(foldedTime)
//        this.foldedTime = foldedTime
//    }
//
//    fun getUnfoldedTime(): Int {
//        return unfoldedTime
//    }
//
//    /**
//     * Must be >= 0.
//     */
//    fun setUnfoldedTime(unfoldedTime: Int) {
//        AppUtils.validateTime(unfoldedTime)
//        this.unfoldedTime = unfoldedTime
//    }
//
//    fun getState(): String {
//        return state
//    }
//
//    /**
//     * Must be one of "fold" or "unfold".
//     */
//    fun setState(state: String) {
//        AppUtils.validateCylinderState(state)
//        state = state
//    }
}