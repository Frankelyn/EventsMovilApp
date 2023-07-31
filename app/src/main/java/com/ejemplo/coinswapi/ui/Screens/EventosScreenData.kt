package com.ejemplo.coinswapi.ui.Screens

import com.ejemplo.coinswapi.data.remote.Dto.SeccionDto


class EventosScreenData {
    companion object {
        var selectedEventId: Int = -1
        var selectedSectionId: Int = -1
        var selectedSection: SeccionDto = SeccionDto()
    }
}
