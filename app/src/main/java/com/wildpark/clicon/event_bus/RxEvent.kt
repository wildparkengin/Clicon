package com.wildpark.clicon.event_bus

import com.wildpark.clicon.enums.EventType

class RxEvent {
    data class EventChanges(
        val changesEvent:EventType
    )
}