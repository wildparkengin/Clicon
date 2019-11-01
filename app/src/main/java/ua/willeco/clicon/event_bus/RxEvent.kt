package ua.willeco.clicon.event_bus

import ua.willeco.clicon.enums.EventType

class RxEvent {
    data class EventChanges(
        val changesEvent: EventType
    )
}