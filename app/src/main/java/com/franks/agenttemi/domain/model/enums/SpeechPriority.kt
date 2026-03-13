package com.franks.agenttemi.domain.model.enums

enum class SpeechPriority {
    USER_REQUEST, //Lo ha pedido el usuario máxima prioridad
    ALERT, //Alertas ambientales
    INFO //Mensajes automaticos minima prioridad
}