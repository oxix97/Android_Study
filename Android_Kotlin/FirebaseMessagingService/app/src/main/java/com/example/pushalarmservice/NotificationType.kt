package com.example.pushalarmservice

enum class NotificationType(
    val title: String,
    val id: Int,
) {
    NORMAL("일반 알림", 0),
    EXPANDABLE("확정형 알림", 1),
    CUSTOM("커스텀 알림", 2)
}